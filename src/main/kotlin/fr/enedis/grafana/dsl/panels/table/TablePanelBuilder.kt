/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table

import org.json.JSONObject
import fr.enedis.grafana.dsl.DashboardElement
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Graphite
import fr.enedis.grafana.dsl.datasource.GraphiteDatasource
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.metrics.ReferenceMetricsHolder
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.repeat.RepeatBuilder
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformationsBuilder
import fr.enedis.grafana.dsl.time.Duration
import fr.enedis.grafana.dsl.variables.Variable

@DashboardElement
class TablePanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {

    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    var stack = false

    val metrics = ReferenceMetricsHolder()

    var description: String? = null

    var timeFrom: Duration? = null

    var position: Position? = null

    var columns: List<TableColumn> = emptyList()

    var styles: List<ColumnStyle> = emptyList()

    var transform: TableTransform = TableTransform.TIMESERIES_AGGREGATIONS

    var transformations: List<PanelTransformation> = mutableListOf()

    var fieldConfig: TablePanelFieldConfig? = null

    var options: TablePanelDisplayOptions? = TablePanelDisplayOptions()


    private var repeat: Repeat? = null

    private val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        propertiesSetters += propertiesSetter
    }

    fun metrics(build: MetricsBuilder<out GraphiteDatasource>.() -> Unit) {
        val builder = MetricsBuilder<Graphite>()
        builder.build()
        metrics += builder.metrics
        seriesOverrides += builder.seriesOverrides
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics += builder.metrics
        seriesOverrides += builder.seriesOverrides
        this.datasource = datasource
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    fun columns(build: TableColumnsBuilder.() -> Unit) {
        val builder = TableColumnsBuilder()
        builder.build()
        columns = builder.columns
    }

    fun styles(build: ColumnStyleBuilder.() -> Unit) {
        val builder = ColumnStyleBuilder()
        builder.build()
        styles = builder.styles
    }

    fun fieldConfig(build: TablePanelFieldConfigBuilder.() -> Unit) {
        val builder = TablePanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createTablePanelFieldConfig()
    }

    fun transformations(build: PanelTransformationsBuilder.() -> Unit) {
        val builder = PanelTransformationsBuilder()
        builder.build()
        transformations = builder.createPanelTransformations()
    }

    fun options(build: TablePanelDisplayOptionsBuilder.() -> Unit) {
        val builder = TablePanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createTablePanelDisplayOptions()
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
        TablePanel(
            MetricPanel(
                BasePanel(
                    id = panelLayoutGenerator.nextId(),
                    title = title,
                    position = position ?: panelLayoutGenerator.nextPosition(bounds.first, bounds.second),
                    description = description
                ),
                datasource = datasource,
                metrics = Metrics(metrics.metrics)
            ),
            timeFrom = timeFrom,
            columns = columns,
            styles = styles,
            repeat = repeat,
            transform = transform,
            fieldConfig = fieldConfig,
            transformations = transformations,
            options = options

        )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.tablePanel(title: String, build: TablePanelBuilder.() -> Unit) {
    val builder = TablePanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
