/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.barGauge

import org.json.JSONObject
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.DashboardMetric
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.repeat.RepeatBuilder
import fr.enedis.grafana.dsl.variables.Variable

class BarGaugePanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {
    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private var timerange = TimeRange()

    private var repeat: Repeat? = null

    var metrics: List<DashboardMetric> = mutableListOf()

    var datasource: Datasource = Zabbix

    var options: BarGaugePanelDisplayOptions = BarGaugePanelDisplayOptions()

    var fieldConfig: BarGaugePanelFieldConfig = BarGaugePanelFieldConfig()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: BarGaugePanelDisplayOptionsBuilder.() -> Unit) {
        val builder = BarGaugePanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createBarGaugePanelDisplayOptions()
    }

    fun fieldConfig(build: BarGaugePanelFieldConfigBuilder.() -> Unit) {
        val builder = BarGaugePanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createBarGaugePanelFieldConfig()
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
         val builder = RepeatBuilder(variable)
         builder.build()
         repeat = builder.createRepeat()
     }

    @Deprecated(message = "pass datasource as the first function argument explicitly")
    inline fun <reified T : Datasource> metrics(build: MetricsBuilder<T>.() -> Unit) {
        datasource = T::class.objectInstance ?: Zabbix
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics = builder.metrics
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics = builder.metrics
        this.datasource = datasource
    }

    fun timerange(build: TimerangeBuilder.() -> Unit) {
        val builder = TimerangeBuilder()
        builder.build()
        timerange = builder.createTimerange()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            BarGaugePanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
                ),
                repeat = repeat,
                timerange = timerange,
                options = options,
                fieldConfig = fieldConfig
            ),
            propertiesSetter
        )
    }
}

fun PanelContainerBuilder.barGaugePanel(title: String, build: BarGaugePanelBuilder.() -> Unit) {
    val builder = BarGaugePanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}