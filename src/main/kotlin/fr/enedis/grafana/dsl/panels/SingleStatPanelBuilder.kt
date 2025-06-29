/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.DashboardMetric
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.repeat.RepeatBuilder
import fr.enedis.grafana.dsl.panels.stat.StatPanelDisplayOptions
import fr.enedis.grafana.dsl.variables.Variable

/**
 * Builder for Singlestat tab
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
@Deprecated("Single stat deprecated in Grafana 7.0")
class SingleStatPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    private var repeat: Repeat? = null

    private var timerange = TimeRange()

    var metrics: List<DashboardMetric> = mutableListOf()

    var valueMappings: ValueMappings = ValueMappings(ValueToTextType)

    var datasource: Datasource = Zabbix

    var options: StatPanelDisplayOptions = StatPanelDisplayOptions()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    @Deprecated(message = "pass datasource as the first function argument instead")
    inline fun <reified T : Datasource> metrics(build: MetricsBuilder<T>.() -> Unit) {
        datasource = T::class.objectInstance ?: Zabbix
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics = builder.metrics
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        this.metrics = builder.metrics
        this.datasource = datasource
    }

    inline fun <reified T : ValueMappingType> valueMappings(build: ValueMappingsBuilder<T>.() -> Unit) {
        val builder = ValueMappingsBuilder<T>()
        builder.build()
        valueMappings = builder.createValueMappings<T>()
    }

    fun timerange(build: TimerangeBuilder.() -> Unit) {
        val builder = TimerangeBuilder()
        builder.build()
        timerange = builder.createTimerange()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            SingleStatPanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
                ),
                valueMappings = valueMappings,
                repeat = repeat,
                timerange = timerange
            ),
            propertiesSetter
        )
    }
}

@Deprecated("Single stat deprecated in Grafana 7.0")
fun PanelContainerBuilder.singleStat(title: String, build: SingleStatPanelBuilder.() -> Unit) {
    val builder = SingleStatPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}