package fr.enedis.grafana.dsl.panels.timeSeries


import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation
import fr.enedis.grafana.dsl.panels.barChart.ShowValueMode
import fr.enedis.grafana.dsl.panels.barChart.StackingMode

class TimeSeriesPanelDisplayOptions(
    private val reduceOptions: TimeSeriesPanelReduceOptions = TimeSeriesPanelReduceOptions(),
    private val displayLabels: List<String> = emptyList<String>(),
    private val showValue: ShowValueMode = ShowValueMode.NEVER,
    private val stacking: StackingMode = StackingMode.NORMAL,
    private val xTickLabelSpacing: Int = 0
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "displayLabels" to displayLabels
        "showValue" to showValue.value
        "stacking" to stacking.value
        "xTickLabelSpacing" to xTickLabelSpacing
    }
}

class TimeSeriesPanelDisplayOptionsBuilder {
    private var reduceOptions: TimeSeriesPanelReduceOptions = TimeSeriesPanelReduceOptions()
    var orientation: Orientation = Orientation.AUTO
    var displayLabels: List<String> = emptyList<String>()
    var showValue: ShowValueMode = ShowValueMode.NEVER
    var stacking: StackingMode = StackingMode.NORMAL
    var xTickLabelSpacing: Int = 0

    fun reduceOptions(build: GaugePanelReduceOptionsBuilder.() -> Unit) {
        val builder = GaugePanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun createTimeSeriesPanelDisplayOptions() = TimeSeriesPanelDisplayOptions(
        reduceOptions = reduceOptions,
        displayLabels = displayLabels,
        showValue = showValue,
        stacking = stacking,
        xTickLabelSpacing = xTickLabelSpacing
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

class GaugePanelReduceOptionsBuilder {
    var fields: String = ""
    var values: Boolean = false
    var calcs: List<String> = listOf("lastNotNull")
    fun createReduceOptions(): TimeSeriesPanelReduceOptions {
        return TimeSeriesPanelReduceOptions(fields = fields, values = values, calcs = calcs)
    }
}

