package fr.enedis.grafana.dsl.panels.timeSeries;

import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Graphite
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.metrics.ReferenceMetricsHolder
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.repeat.RepeatBuilder
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformationsBuilder
import fr.enedis.grafana.dsl.variables.Variable
import org.json.JSONObject

class TimeSeriesPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator,
) : PanelBuilder {
    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    private var timerange = TimeRange()

    private var repeat: Repeat? = null

    var metrics = ReferenceMetricsHolder()

    var options: TimeSeriesPanelDisplayOptions = TimeSeriesPanelDisplayOptions()

    var fieldConfig: TimeSeriesPanelFieldConfig = TimeSeriesPanelFieldConfig()

    var transformations: List<PanelTransformation> = mutableListOf()

    var description: String? = null

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetters += propertiesSetter
    }

    fun options(build: TimeSeriesPanelDisplayOptionsBuilder.() -> Unit) {
        val builder = TimeSeriesPanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createTimeSeriesPanelDisplayOptions()
    }

    fun fieldConfig(build: TimeSeriesPanelFieldConfigBuilder.() -> Unit) {
        val builder = TimeSeriesPanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createTimeSeriesPanelFieldConfig()
    }

    fun transformations(build: PanelTransformationsBuilder.() -> Unit) {
        val builder = PanelTransformationsBuilder()
        builder.build()
        transformations = builder.createPanelTransformations()
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics += builder.metrics
        this.datasource = datasource
    }

    fun timeRange(build: TimerangeBuilder.() -> Unit) {
        val builder = TimerangeBuilder()
        builder.build()
        timerange = builder.createTimerange()
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
        TimeSeriesPanel(
            MetricPanel(
                BasePanel(
                    id = panelLayoutGenerator.nextId(),
                    title = title,
                    position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second),
                    description = description
                ),
                datasource = datasource,
                metrics = Metrics(metrics.metrics)
            ),
            repeat = repeat,
            timeRange = timerange,
            options = options,
            fieldConfig = fieldConfig,
            transformations = transformations,
        )
    ) {
        json -> propertiesSetters.forEach { it(json) }
    }

}

fun PanelContainerBuilder.timeSeriesPanel(title: String, build: TimeSeriesPanelBuilder.() -> Unit) {
    val builder = TimeSeriesPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
