package fr.enedis.grafana.dsl.panels.timeSeries


import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation
import fr.enedis.grafana.dsl.panels.barChart.ShowValueMode
import fr.enedis.grafana.dsl.panels.barChart.StackingMode
import org.json.JSONObject

class TimeSeriesPanelDisplayOptions(
    private val reduceOptions: TimeSeriesPanelReduceOptions = TimeSeriesPanelReduceOptions(),
    private val displayLabels: List<String> = emptyList(),
    private val showValue: ShowValueMode = ShowValueMode.NEVER,
    private val stacking: StackingMode = StackingMode.NORMAL,
    private val xTickLabelSpacing: Int = 0,
    private val legend: Legend = Legend.DEFAULT

) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "displayLabels" to displayLabels
        "showValue" to showValue.value
        "stacking" to stacking.value
        "xTickLabelSpacing" to xTickLabelSpacing
        "legend" to legend
    }
}

class TimeSeriesPanelDisplayOptionsBuilder {
    private var reduceOptions: TimeSeriesPanelReduceOptions = TimeSeriesPanelReduceOptions()
    var orientation: Orientation = Orientation.AUTO
    var displayLabels: List<String> = emptyList<String>()
    var showValue: ShowValueMode = ShowValueMode.NEVER
    var stacking: StackingMode = StackingMode.NORMAL
    var xTickLabelSpacing: Int = 0
    var legend: Legend = Legend.DEFAULT

    fun createTimeSeriesPanelDisplayOptions() = TimeSeriesPanelDisplayOptions(
        reduceOptions = reduceOptions,
        displayLabels = displayLabels,
        showValue = showValue,
        stacking = stacking,
        xTickLabelSpacing = xTickLabelSpacing,
        legend = legend
    )
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

