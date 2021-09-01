package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.Graphite
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.metrics.Metrics
import ru.yoomoney.tech.grafana.dsl.metrics.MetricsBuilder
import ru.yoomoney.tech.grafana.dsl.metrics.ReferenceMetricsHolder
import ru.yoomoney.tech.grafana.dsl.metrics.functions.Alias
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.drawoptions.HoverTooltip
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverrideBuilder
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Repeat
import ru.yoomoney.tech.grafana.dsl.panels.repeat.RepeatBuilder
import ru.yoomoney.tech.grafana.dsl.time.Duration
import ru.yoomoney.tech.grafana.dsl.variables.Variable

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

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
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
