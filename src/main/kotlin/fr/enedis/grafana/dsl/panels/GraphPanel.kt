package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.emptyJsonArray
import fr.enedis.grafana.dsl.json.jsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.graph.display.drawoptions.HoverTooltip
import fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.time.Duration

/**
 * Common chart with legend, axis, thresholds.
 *
 * @author Dmitry Komarov
 * @since 7/23/18
 */
class GraphPanel(
    private val basePanel: Panel,
    private val timeShift: Duration? = null,
    private val stack: Boolean = false,
    private val legend: Legend = Legend.DEFAULT,
    private val decimals: Int? = null,
    private val points: Boolean = false,
    private val bars: Boolean = false,
    private val lines: Boolean = true,
    private val pointradius: Int = 5,
    private val nullValue: NullValue = NullValue.NULL,
    private val fill: Int = 1,
    private val fillGradient: Int = 0,
    private val lineWidth: Int = 1,
    private val staircase: Boolean = false,
    private val hoverTooltip: HoverTooltip = HoverTooltip(),
    private val aliasColors: AliasColors? = null,
    private val leftYAxis: YAxis? = null,
    private val rightYAxis: YAxis? = null,
    private val xAxis: XAxis = XAxis(),
    private val seriesOverrides: List<SeriesOverride> = emptyList(),
    private val repeat: Repeat? = null,
    private val fieldConfig: GraphPanelFieldConfig = GraphPanelFieldConfig(),
    ) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "graph"
        "legend" to legend
        "bars" to bars
        "lines" to lines
        "points" to points
        "dashes" to false
        "steppedLine" to staircase
        "stack" to stack
        "dashLength" to 10
        "decimals" to decimals
        "linewidth" to lineWidth
        "spaceLength" to 10
        "pointradius" to pointradius
        "percentage" to false
        "renderer" to "flot"
        "fill" to fill
        "fieldConfig" to fieldConfig
        "fillGradient" to fillGradient
        "seriesOverrides" to JsonArray(seriesOverrides)
        "thresholds" to emptyJsonArray()
        "timeShift" to timeShift?.toString()
        "nullPointMode" to nullValue.value
        "aliasColors" to aliasColors
        "tooltip" to hoverTooltip
        "xaxis" to xAxis
        "yaxis" to jsonObject {
            "align" to false
        }
        "yaxes" to jsonArray[leftYAxis ?: YAxis(), rightYAxis ?: YAxis()]
        if (repeat != null) {
            embed(repeat)
        }
    }
}
