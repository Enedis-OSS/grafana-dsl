/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import ElasticDatasource
import ExprDataSource
import fr.enedis.grafana.dsl.DashboardElement
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.ZabbixDatasource
import fr.enedis.grafana.dsl.metrics.functions.Alias
import fr.enedis.grafana.dsl.metrics.prometheus.PrometheusMetric
import fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverrideBuilder

@DashboardElement
class MetricsBuilder<DatasourceT : Datasource> {

    val metrics = mutableListOf<DashboardMetric>()
    internal val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()
    private val metricIdGenerator by lazy { MetricIdGenerator() }

    fun metric(referenceId: String? = null, hidden: Boolean = false, fn: () -> Metric): String {
        val id = referenceId ?: generateMetricId()
        metrics += ReferencedDashboardMetric(fn(), id, hidden)
        return id
    }

    fun prometheusMetric(
        legendFormat: String? = null,
        instant: Boolean = false,
        referenceId: String? = null,
        hidden: Boolean = false,
        fn: () -> PrometheusMetric,
    ): String {
        val id = referenceId ?: generateMetricId()
        metrics += PromQlMetric(fn(), legendFormat, instant, id, hidden)
        return id
    }

    private fun generateMetricId(): String {
        var generatedId: String
        do {
            generatedId = metricIdGenerator.nextMetricId()
        } while (metrics.map { it.id }.contains(generatedId))
        return generatedId
    }

    @JvmName("metricsQueryZabbixDatasource")
    fun MetricsBuilder<out ZabbixDatasource>.metricsQuery(build: ZabbixMetric.Builder.Metric.() -> Unit = {}) {
        val builder = ZabbixMetric.Builder.Metric()
        builder.build()
        metrics += builder.createMetric()
    }

    fun MetricsBuilder<out ZabbixDatasource>.textQuery(build: ZabbixMetric.Builder.Text.() -> Unit = {}) {
        val builder = ZabbixMetric.Builder.Text()
        builder.build()
        metrics += builder.createText()
    }

    infix fun Alias.override(build: SeriesOverrideBuilder.() -> Unit): Alias {
        val builder = SeriesOverrideBuilder(this.aliasName)
        builder.build()
        seriesOverrides += builder.createSeriesOverride()
        return this
    }

    @JvmName("metricsQueryElasticDatasource")
    fun MetricsBuilder<out ElasticDatasource>.metricsQuery(referenceId: String? = null, build: ElasticMetric.Builder.Metric.() -> Unit = {}) {
        val builder = ElasticMetric.Builder.Metric().apply {
            id = referenceId ?: generateMetricId()
        }
        builder.build()
        metrics += builder.createMetric()
    }
    @JvmName("expression")
    fun MetricsBuilder<out ExprDataSource>.expression(referenceId: String? = null, build: Expression.Builder.Metric.() -> Unit = {}) {
        val builder = Expression.Builder.Metric().apply {
            id = referenceId ?: generateMetricId()
        }
        builder.build()
        metrics += builder.createMetric()
    }
}