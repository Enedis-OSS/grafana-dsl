package fr.enedis.grafana.dsl.variables

import org.json.JSONObject
import fr.enedis.grafana.dsl.dashboard.Tags
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Feature to group the values into selectable tags
 * @author Aleksandr Korkin
 */
class VariableTags(
    private val tagsQuery: String,
    private val tagValuesQuery: String,
    private val tags: Tags? = null
) : Json<JSONObject> {
    override fun toJson() = jsonObject {
        "tagsQuery" to tagsQuery
        "tagValuesQuery" to tagValuesQuery
        "useTags" to true
    }
}
