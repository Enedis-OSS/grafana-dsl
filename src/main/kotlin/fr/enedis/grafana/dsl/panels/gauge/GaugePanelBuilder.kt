package fr.enedis.grafana.dsl.panels.gauge

import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.DashboardMetric
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.repeat.RepeatBuilder
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformationsBuilder
import fr.enedis.grafana.dsl.variables.Variable
import org.json.JSONObject

/**
 * Builder for Stat tab
 * @author Aleksey Matveev
 * @since 29.10.2020
 */
class GaugePanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator,
) : PanelBuilder {
    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private var timerange = Timerange()

    private var repeat: Repeat? = null

    var metrics: List<DashboardMetric> = mutableListOf()

    var datasource: Datasource = Zabbix

    var options: GaugePanelDisplayOptions = GaugePanelDisplayOptions()

    var fieldConfig: GaugePanelFieldConfig = GaugePanelFieldConfig()

    var transformations: List<PanelTransformation> = mutableListOf()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: GaugePanelDisplayOptionsBuilder.() -> Unit) {
        val builder = GaugePanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createGaugePanelDisplayOptions()
    }

    fun fieldConfig(build: GaugePanelFieldConfigBuilder.() -> Unit) {
        val builder = GaugePanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createGaugePanelFieldConfig()
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
            GaugePanel(
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
                fieldConfig = fieldConfig,
                transformations = transformations,
            ),
            propertiesSetter
        )
    }
}

fun PanelContainerBuilder.gaugePanel(title: String, build: GaugePanelBuilder.() -> Unit) {
    val builder = GaugePanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
