package fr.enedis.grafana.dsl.panels.stateTimeline

import ExprDataSource

import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.DashboardMetric
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.panels.PanelBuilder
import fr.enedis.grafana.dsl.panels.PanelContainerBuilder
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformationsBuilder
import fr.enedis.grafana.dsl.variables.Variable
import org.json.JSONObject
import fr.enedis.grafana.dsl.panels.repeat.*

class StateTimelineBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {
    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private var timerange = TimeRange()

    private var repeat: Repeat? = null

    var position: Position? = null

    var metrics: List<DashboardMetric> = mutableListOf()

    var expressions: List<DashboardMetric> = mutableListOf()

    var datasource: Datasource = Zabbix

    var options: StateTimelineDisplayOptions = StateTimelineDisplayOptions()

    var fieldConfig: StateTimelineFieldConfig = StateTimelineFieldConfig()

    var transformations: List<PanelTransformation> = mutableListOf()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: StatPanelDisplayOptionsBuilder.() -> Unit) {
        val builder = StatPanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createStatPanelDisplayOptions()
    }

    fun transformations(build: PanelTransformationsBuilder.() -> Unit) {
        val builder = PanelTransformationsBuilder()
        builder.build()
        transformations = builder.createPanelTransformations()
    }

    fun fieldConfig(build: StateTimelineFieldConfigBuilder.() -> Unit) {
        val builder = StateTimelineFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createStateTimelineFieldConfig()
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        this.metrics = builder.metrics
        this.datasource = datasource
    }

    fun expressions(build: MetricsBuilder<ExprDataSource>.() -> Unit) {
        val builder = MetricsBuilder<ExprDataSource>()
        builder.build()
        this.expressions = builder.metrics
    }

    fun timerange(build: TimerangeBuilder.() -> Unit) {
        val builder = TimerangeBuilder()
        builder.build()
        timerange = builder.createTimerange()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            StateTimelinePanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = position ?: panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics + expressions)
                ),
                repeat = repeat,
                timerange = timerange,
                options = options,
                fieldConfig = fieldConfig,
                transformations = transformations
            ),
            propertiesSetter
        )
    }

}


fun PanelContainerBuilder.stateTimeline(title: String, build: StateTimelineBuilder.() -> Unit) {
    val builder = StateTimelineBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
