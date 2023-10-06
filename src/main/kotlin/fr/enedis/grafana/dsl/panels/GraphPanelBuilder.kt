package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Graphite
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder
import fr.enedis.grafana.dsl.metrics.ReferenceMetricsHolder
import fr.enedis.grafana.dsl.metrics.functions.Alias
import fr.enedis.grafana.dsl.panels.gauge.GaugePanelFieldConfigBuilder
import fr.enedis.grafana.dsl.panels.graph.display.drawoptions.HoverTooltip
import fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverrideBuilder
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.repeat.RepeatBuilder
import fr.enedis.grafana.dsl.time.Duration
import fr.enedis.grafana.dsl.variables.Variable

class GraphPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {

    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    var timeShift: Duration? = null

    var stack = false

    val metrics = ReferenceMetricsHolder()

    var legend = Legend.DEFAULT

    var decimals: Int? = null

    var points = false

    var lines = true

    var bars = false

    var pointradius = 5

    var nullValue = NullValue.NULL

    var fill = 1

    var fillGradient = 0

    var lineWidth = 1

    var staircase = false

    var hoverTooltip = HoverTooltip()

    var aliasColors: AliasColors? = null

    var leftYAxis: YAxis? = null

    var rightYAxis: YAxis? = null

    var xAxis: XAxis = XAxis()

    var description: String? = null

    var fieldConfig: GraphPanelFieldConfig = GraphPanelFieldConfig()

    private var repeat: Repeat? = null

    private val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        propertiesSetters += propertiesSetter
    }

    fun aliasColors(build: AliasColorsBuilder.() -> Unit) {
        val builder = AliasColorsBuilder()
        builder.build()
        aliasColors = builder.aliasColors
    }

    @Deprecated(message = "pass datasource as the first function argument explicitly")
    fun metrics(build: MetricsBuilder<Graphite>.() -> Unit) {
        val builder = MetricsBuilder<Graphite>()
        builder.build()
        metrics += builder.metrics
        seriesOverrides += builder.seriesOverrides
        datasource = Graphite
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics += builder.metrics
        seriesOverrides += builder.seriesOverrides
        this.datasource = datasource
    }

    infix fun Alias.override(build: SeriesOverrideBuilder.() -> Unit): Alias {
        val builder = SeriesOverrideBuilder(this.aliasName)
        builder.build()
        seriesOverrides += builder.createSeriesOverride()
        return this
    }

    fun fieldConfig(build: GraphPanelFieldConfigBuilder.() -> Unit) {
        val builder = GraphPanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createGraphPanelFieldConfig()
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    fun legend(build: LegendBuilder.() -> Unit) {
        val builder = LegendBuilder()
        builder.build()
        legend = builder.createLegend()
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
        GraphPanel(
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
            timeShift = timeShift,
            stack = stack,
            legend = legend,
            fieldConfig = fieldConfig,
            decimals = decimals,
            bars = bars,
            lines = lines,
            points = points,
            pointradius = pointradius,
            nullValue = nullValue,
            fill = fill,
            fillGradient = fillGradient,
            lineWidth = lineWidth,
            staircase = staircase,
            hoverTooltip = hoverTooltip,
            aliasColors = aliasColors,
            leftYAxis = leftYAxis,
            rightYAxis = rightYAxis,
            seriesOverrides = seriesOverrides,
            xAxis = xAxis,
            repeat = repeat
        )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.graphPanel(title: String, build: GraphPanelBuilder.() -> Unit) {
    val builder = GraphPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
