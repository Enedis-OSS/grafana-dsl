package fr.enedis.grafana.dsl.panels.stat

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject

class StatPanelTransformation(
    private val id: String,
    private val options: CalculateFieldOptions,
) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "options" to jsonObject {
            "mode" to options.mode
            "reduce" to jsonObject {
                "reducer" to options.calculation
                "include" to JsonArray(emptyList())
            }
            "replaceFields" to options.replaceFields
        }
    }
}
