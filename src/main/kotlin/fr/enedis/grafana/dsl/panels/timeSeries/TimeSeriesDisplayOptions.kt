package fr.enedis.grafana.dsl.panels.timeSeries


import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation
import fr.enedis.grafana.dsl.panels.barChart.ShowValueMode
import org.json.JSONObject

class TimeSeriesPanelDisplayOptions(
    private val reduceOptions: TimeSeriesPanelReduceOptions = TimeSeriesPanelReduceOptions(),
    private val displayLabels: List<String> = emptyList(),
    private val showValue: ShowValueMode = ShowValueMode.NEVER,
    private val stacking: StackingMode = StackingMode.NORMAL,
    private val xTickLabelSpacing: Int = 0,
    private val legend: TimeSeriesLegend = TimeSeriesLegend.DEFAULT,
    private val timezone: List<String>? = null,
    private val tooltip: TimeSeriesTooltip = TimeSeriesTooltip()

) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "displayLabels" to displayLabels
        "showValue" to showValue.value
        "stacking" to stacking.value
        "xTickLabelSpacing" to xTickLabelSpacing
        "legend" to legend
        "timezone" to timezone
        "tooltip" to tooltip
    }
}

class TimeSeriesTooltip(
    private val mode: TooltipDisplayMode = TooltipDisplayMode.SINGLE,
    private val sort: SortOrder = SortOrder.NONE,
    private val maxHeight: Int? = null,
    private val maxWidth: Int? = null,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode.value
        "sort" to sort.value
        "maxHeight" to maxHeight
        "maxWidth" to maxWidth
    }
}

class TimeSeriesPanelDisplayOptionsBuilder {
    private var reduceOptions: TimeSeriesPanelReduceOptions = TimeSeriesPanelReduceOptions()
    var orientation: Orientation = Orientation.AUTO
    var displayLabels: List<String> = emptyList()
    var showValue: ShowValueMode = ShowValueMode.NEVER
    var stacking: StackingMode = StackingMode.NORMAL
    var xTickLabelSpacing: Int = 0
    var legend: TimeSeriesLegend = TimeSeriesLegend.DEFAULT
    var timezone: List<String>? = null
    var tooltip: TimeSeriesTooltip = TimeSeriesTooltip()

    fun createTimeSeriesPanelDisplayOptions() = TimeSeriesPanelDisplayOptions(
        reduceOptions = reduceOptions,
        displayLabels = displayLabels,
        showValue = showValue,
        stacking = stacking,
        xTickLabelSpacing = xTickLabelSpacing,
        legend = legend,
        timezone = timezone,
        tooltip = tooltip,
    )

    fun legend(build: TimeSeriesLegendBuilder.() -> Unit) {
        val builder = TimeSeriesLegendBuilder()
        builder.build()
        legend = builder.createTimeSeriesLegend()
    }
}

class TimeSeriesPanelReduceOptions(
    private val values: Boolean = false,
    private val calcs: List<String> = listOf("lastNotNull"),
    private val fields: String = "",
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to values
        "calcs" to calcs
        "fields" to fields
    }
}

enum class TooltipDisplayMode(val value: String) {
    MULTI("multi"),
    NONE("none"),
    SINGLE("single");
}

enum class SortOrder(val value: String) {
    ASC("asc"),
    DESC("desc"),
    NONE("none");
}


