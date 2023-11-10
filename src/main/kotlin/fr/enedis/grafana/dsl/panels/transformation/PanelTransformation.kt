package fr.enedis.grafana.dsl.panels.transformation

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

class PanelTransformation(
    private val id: String,
    private val options: TransformationOptionsBuilder,
) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "options" to options
    }
}
