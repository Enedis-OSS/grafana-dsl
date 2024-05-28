package fr.enedis.grafana.dsl.panels.pieChart

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class LegendPieChart(
    private val displayMode: DisplayModePieChart = DisplayModePieChart.LIST,
    private val placement: ComponentPlacementPieChart = ComponentPlacementPieChart.BOTTOM
) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "displayMode" to displayMode.value
        "placement" to placement.value
        if (DisplayModePieChart.HIDDEN == displayMode) {
            "showLegend" to false
        }
    }
}

class LegendPieChartBuilder {
    var displayMode: DisplayModePieChart = DisplayModePieChart.LIST
    var placement: ComponentPlacementPieChart = ComponentPlacementPieChart.BOTTOM
    fun createLegend(): LegendPieChart {
        return LegendPieChart(displayMode = displayMode, placement = placement)
    }
}