package fr.enedis.grafana.dsl.panels.timeSeries

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class Legend(
    private val mode: LegendMode = LegendMode.LIST,
    private val placement: LegendPlacement = LegendPlacement.BOTTOM,
) : Json<JSONObject> {

    companion object Factory {

        /**
         * Default block "legend"
         */
        val DEFAULT = Legend()

        /**
         * Hidden block "legend"
         */
        val HIDDEN = Legend(
            mode = LegendMode.HIDDEN
        )
    }

    override fun toJson() = jsonObject {
        "displayMode" to mode.value
        "placement" to placement.value
        "calcs" to emptyList<String>()
    }
}

enum class LegendPlacement(val value: String) {
    BOTTOM("bottom"), RIGHT("right")
}

enum class LegendMode(val value: String) {
    HIDDEN("hidden"),
    TABLE("table"),
    LIST("list")
}
