package fr.enedis.grafana.dsl.panels.timeSeries

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class Custom(
    private val fillOpacity: Double = 20.0,
    private val showPoints: ShowPointsMode = ShowPointsMode.NEVER
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "fillOpacity" to fillOpacity
        "showPoints" to showPoints.value
    }
}