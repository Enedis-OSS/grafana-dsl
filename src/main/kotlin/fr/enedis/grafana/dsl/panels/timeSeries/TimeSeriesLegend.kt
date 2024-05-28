package fr.enedis.grafana.dsl.panels.timeSeries

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class TimeSeriesLegend(
    private val mode: LegendMode = LegendMode.LIST,
    private val placement: LegendPlacement = LegendPlacement.BOTTOM,
    private val calcs: List<String> = emptyList(),
    private val isVisible: Boolean? = null,
    private val showLegend: Boolean? = null,
    private val sortBy: String? = null,
    private val sortDesc: Boolean? = null,
    private val width: Int? = null

) : Json<JSONObject> {

    companion object Factory {

        /**
         * Default block "legend"
         */
        val DEFAULT = TimeSeriesLegend()

        /**
         * Hidden block "legend"
         */
        val HIDDEN = TimeSeriesLegend(
            mode = LegendMode.HIDDEN
        )
    }

    override fun toJson() = jsonObject {
        "displayMode" to mode.value
        "placement" to placement.value
        "calcs" to calcs
        "isVisible" to isVisible
        if (showLegend == null) {
            if (LegendMode.HIDDEN == mode) {
                "showLegend" to false
            } else {
                "showLegend" to true
            }
        } else {
            "showLegend" to showLegend
        }
        "sortBy" to sortBy
        "sortDesc" to sortDesc
        "width" to width
    }
}

enum class LegendPlacement(val value: String) {
    BOTTOM("bottom"), RIGHT("right")
}

enum class LegendMode(val value: String) {
    HIDDEN("hidden"), TABLE("table"), LIST("list")
}

class TimeSeriesLegendBuilder {
    var mode: LegendMode = LegendMode.LIST
    var placement: LegendPlacement = LegendPlacement.BOTTOM
    var calcs: List<String> = emptyList()
    var isVisible: Boolean? = null
    var showLegend: Boolean? = null
    var sortBy: String? = null
    var sortDesc: Boolean? = null
    var width: Int? = null

    fun createTimeSeriesLegend() = TimeSeriesLegend(
        mode = mode,
        placement = placement,
        calcs = calcs,
        isVisible = isVisible,
        showLegend = showLegend,
        sortBy = sortBy,
        sortDesc = sortDesc,
        width = width,
    )
}