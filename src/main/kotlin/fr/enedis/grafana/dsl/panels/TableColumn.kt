package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * The Columns option allows you to select what columns you want in the table.
 *
 * @author Aleksandr Korkin
 * @since 16.12.2019
 */
class TableColumn(val value: String, private val name: String = value) : Json<JSONObject> {
    override fun toJson() = jsonObject {
        "text" to name
        "value" to value
    }
}
