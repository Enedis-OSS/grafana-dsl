package fr.enedis.grafana.dsl.panels.stat

import ExprDataSource
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
import org.json.JSONObject

/**
 * Builder for Stat tab
 * @author Aleksey Matveev
 * @since 29.10.2020
 */
class StatPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {
    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private var timerange = Timerange()

    private var repeat: Repeat? = null

    var position: Position? = null

    var metrics: List<DashboardMetric> = mutableListOf()

    var expressions: List<DashboardMetric> = mutableListOf()

    var datasource: Datasource = Zabbix

    var options: StatPanelDisplayOptions = StatPanelDisplayOptions()

    var fieldConfig: StatPanelFieldConfig = StatPanelFieldConfig()

    var transformations: List<StatPanelTransformation> = mutableListOf()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: StatPanelDisplayOptionsBuilder.() -> Unit) {
        val builder = StatPanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createStatPanelDisplayOptions()
    }

    fun transformations(build: StatPanelTransformationsBuilder.() -> Unit) {
        val builder = StatPanelTransformationsBuilder()
        builder.build()
        transformations = builder.createStatPanelTransformations()
    }

    fun fieldConfig(build: StatPanelFieldConfigBuilder.() -> Unit) {
        val builder = StatPanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createStatPanelFieldConfig()
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
            StatPanel(
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

fun PanelContainerBuilder.statPanel(title: String, build: StatPanelBuilder.() -> Unit) {
    val builder = StatPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
