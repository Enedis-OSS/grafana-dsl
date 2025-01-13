/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import org.json.JSONObject
import fr.enedis.grafana.dsl.DashboardElement
import fr.enedis.grafana.dsl.json.*
import fr.enedis.grafana.dsl.time.Duration
import fr.enedis.grafana.dsl.time.m

class ElasticMetric constructor(
    override val id: String,
    private val alias: String?,
    private val query: String,
    private val queryType: String,
    private val timeField: String = "@timestamp",
    private val metrics: List<ElasticQueryMetric> = emptyList(),
    private val groupBys: List<ElasticGroupBy> = emptyList(),
    private val hide: Boolean = false,
) : DashboardMetric {
    override fun toJson(): JSONObject {
        return jsonObject {
            "refId" to id
            "alias" to alias
            "query" to query
            "queryType" to queryType
            "timeField" to timeField
            "metrics" to JsonArray(metrics)
            "bucketAggs" to JsonArray(groupBys)
            "hide" to hide
        }
    }

    @DashboardElement
    class Builder {
        class Metric {
            var id: String? = null
            var alias: String? = null
            var query: String = ""
            var queryType: String = "randomWalk"
            var timeField: String = "@timestamp_second"
            var metrics: List<ElasticQueryMetric> = emptyList()
            var groupBys: List<ElasticGroupBy> = emptyList()
            var hide: Boolean = false

            internal fun createMetric() = ElasticMetric(
                id = id ?: "A",
                alias = alias,
                query = query,
                queryType = queryType,
                timeField = timeField,
                metrics = metrics,
                groupBys = groupBys,
                hide = hide
            )

            fun metrics(build: ElasticQueryMetricsBuilder.() -> Unit): List<ElasticQueryMetric> {
                val builder = ElasticQueryMetricsBuilder()
                builder.build()
                return builder.metrics
            }

            fun groupBys(build: ElasticGroupBysBuilder.() -> Unit): List<ElasticGroupBy> {
                val builder = ElasticGroupBysBuilder()
                builder.build()
                return builder.groupBys
            }
        }
    }
}

@DashboardElement
class ElasticGroupBysBuilder {

    var groupBys = mutableListOf<ElasticGroupBy>()

    private val metricIdGenerator by lazy { ElasticMetricIdGenerator() }

    private fun generateMetricId(): String {
        var generatedId: String
        do {
            generatedId = metricIdGenerator.nextMetricId()
        } while (groupBys.map { it.id }.contains(generatedId))
        return generatedId
    }

    fun groupBy(build: ElasticGroupByBuilder.() -> Unit) {
        val builder = ElasticGroupByBuilder().apply {
            id = this@ElasticGroupBysBuilder.generateMetricId()
        }
        builder.build()
        groupBys += builder.createElasticGroupBy()
    }

    // just semantic
    fun thenBy(build: ElasticGroupByBuilder.() -> Unit) {
        groupBy(build)
    }

    //just shortcuts
    fun dateHistogram(
        _field: String = "@timestamp_second",
        build: ElasticSettingsBuilder.DateHistogramElasticSettings.() -> Unit = {},
    ) = groupBy {
        type = "date_histogram"
        field = _field
        val builder = ElasticSettingsBuilder.DateHistogramElasticSettings()
        builder.build()
        settings = builder.createElasticSettings()
    }

    fun terms(
        _field: String,
        build: ElasticSettingsBuilder.TermsElasticSettings.() -> Unit = {},
    ) = groupBy {
        type = "terms"
        field = _field
        val builder = ElasticSettingsBuilder.TermsElasticSettings()
        builder.build()
        settings = builder.createElasticSettings()
    }
}

@DashboardElement
class ElasticGroupByBuilder {

    var field: String = ""
    var id: String? = null
    var type: String = ""
    var settings: ElasticSettings = ElasticSettings()

    fun createElasticGroupBy() = ElasticGroupBy(
        field = field, id = id, type = type, settings = settings
    )

}


/**
 * Generates metric id
 *
 */
internal class ElasticMetricIdGenerator {
    private var lastId: Int? = null

    /**
     * Return next metric id
     *
     * @throws IllegalStateException if last generated id was 'Z'
     */
    fun nextMetricId(): String {
        val nextId = lastId?.plus(1) ?: 1
        lastId = nextId
        return nextId.toString()
    }
}


@DashboardElement
class ElasticQueryMetricsBuilder {

    var metrics = mutableListOf<ElasticQueryMetric>()

    private val metricIdGenerator by lazy { ElasticMetricIdGenerator() }

    private fun generateMetricId(): String {
        var generatedId: String
        do {
            generatedId = metricIdGenerator.nextMetricId()
        } while (metrics.map { it.id }.contains(generatedId))
        return generatedId
    }

    fun metric(build: ElasticQueryMetricBuilder.() -> Unit) {
        val builder = ElasticQueryMetricBuilder().apply {
            id = this@ElasticQueryMetricsBuilder.generateMetricId()
        }
        builder.build()
        metrics += builder.createElasticQueryMetric()
    }

    fun average(_field: String, _hide: Boolean = false) = metric {
        field = _field
        type = "avg"
        hide = _hide
    }

    fun sum(_field: String, _hide: Boolean = false) = metric {
        field = _field
        type = "sum"
        hide = _hide
    }

    fun max(_field: String) = metric {
        field = _field
        type = "max"
    }
    fun min(_field: String) = metric {
        field = _field
        type = "min"
    }

    fun cardinality(_field: String) = metric {
        field = _field
        type = "cardinality"
    }

    fun count() = metric {
        field = ""
        type = "count"
    }

    fun derivative(_field: String) = metric {
        field = _field
        type = "derivative"
    }
}

@DashboardElement
class ElasticQueryMetricBuilder {
    var id: String? = null
    var type: String = "count"
    var field: String? = null
    var hide: Boolean = false
    var settings: ElasticSettings = ElasticSettings()
    var pipelineVariables: MutableList<ElasticQueryMetricPipelineVariable> = mutableListOf()

    fun bucket_script(script: String) {
        type = "bucket_script"
        settings = ElasticSettings(script = script)
    }

    fun pipelineVariable(build: ElasticQueryMetricPipelineVariableBuilder.() -> Unit) {
        val builder = ElasticQueryMetricPipelineVariableBuilder()
        builder.build()
        pipelineVariables += builder.createElasticQueryMetricPipelineVariable()
    }

    fun createElasticQueryMetric() = ElasticQueryMetric(type = type, field = field, id = id, hide = hide, settings = settings, pipelineVariables = pipelineVariables)
}

@DashboardElement
class ElasticQueryMetricPipelineVariableBuilder {
    var name: String = ""
    var pipelineAgg: String = ""

    fun createElasticQueryMetricPipelineVariable() = ElasticQueryMetricPipelineVariable(name = name, pipelineAgg = pipelineAgg)
}

@DashboardElement
abstract class ElasticSettingsBuilder {

    open var min_doc_count: String? = null

    abstract fun createElasticSettings() : ElasticSettings

    class DateHistogramElasticSettings : ElasticSettingsBuilder() {
        override var min_doc_count: String? = "0"
        override fun createElasticSettings(): ElasticSettings {
            return ElasticSettings(min_doc_count, null, null, null, trimEdges, interval)
        }

        var interval: Duration? = 1.m // "auto"
        var trimEdges: String? = "0"
        var timezone: String? = "utc"
    }

    class TermsElasticSettings : ElasticSettingsBuilder() {
        override var min_doc_count: String? = "1"
        var size: String? = "10"
        var order: String? = "desc"
        var orderBy: String? = "_term"

        override fun createElasticSettings() = ElasticSettings(min_doc_count, order, orderBy, size, null, null)
    }

    class BucketScriptElasticSettings : ElasticSettingsBuilder() {
        var script: String? = ""

        override fun createElasticSettings(): ElasticSettings {
            return ElasticSettings(script = script)
        }
    }

}

class ElasticSettings constructor(
    private val min_doc_count: String? = null,
    private val order: String? = null, // TODO: type order value
    private val orderBy: String? = null,
    private val size: String? = null,
    private val trimEdges: String? = null,
    private val interval: Duration? = null,
    private val timezone: String? = null,
    private val script: String? = null,

) : Json<JSONObject> {
    override fun toJson(): JSONObject {
        return jsonObject {
            "min_doc_count" to min_doc_count
            "order" to order
            "orderBy" to orderBy
            "size" to size
            "trimEdges" to trimEdges
            "interval" to interval
            "timezone" to timezone
            "script" to script
        }
    }
}


class ElasticGroupBy constructor(
    val id: String? = null,
    private val field: String,
    private val type: String, // TODO: fix type values
    private val settings: ElasticSettings,
) : Json<JSONObject> {
    override fun toJson(): JSONObject {
        return jsonObject {
            "field" to field
            "id" to id
            "type" to type
            "settings" to settings
        }
    }
}

class ElasticQueryMetric constructor(
    override val id: String? = "1",
    private val type: String,
    private val field: String?,
    private val hide: Boolean = false,
    private val pipelineVariables: List<ElasticQueryMetricPipelineVariable>,
    private val settings: ElasticSettings = ElasticSettings()
) : DashboardMetric {
    override fun toJson(): JSONObject {
        return jsonObject {
            "id" to id
            "type" to type
            "field" to field
            "meta" to JSONObject()
            "settings" to settings
            "hide" to hide
            "pipelineVariables" to pipelineVariables.toJsonArrayIfNotEmpty()
        }
    }
}

class ElasticQueryMetricPipelineVariable constructor(
    private val name: String,
    private val pipelineAgg: String
) : Json<JSONObject> {
    override fun toJson(): JSONObject {
        return jsonObject {
            "name" to name
            "pipelineAgg" to pipelineAgg
        }
    }
}