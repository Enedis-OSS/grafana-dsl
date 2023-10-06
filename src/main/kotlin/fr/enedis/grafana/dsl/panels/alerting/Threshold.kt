package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.set

class Threshold(private val operation: String, private val value: Int) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["colorMode"] = "critical"
        json["fill"] = true
        json["line"] = true
        json["op"] = operation
        json["value"] = value

        return json
    }
}
