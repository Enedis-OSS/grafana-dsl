package fr.enedis.grafana.dsl.json

import org.json.JSONArray
import org.json.JSONObject

/**
 * Array of JSON objects.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class JsonArray(private val objects: Collection<Json<JSONObject>>) : Json<JSONArray> {
    override fun toJson() = JSONArray(objects.map { it.toJson() })
}

fun Collection<Json<JSONObject>>.toJsonArray(): JsonArray = JsonArray(this)

fun Collection<Json<JSONObject>>.toJsonArrayIfNotEmpty(): JsonArray? = if (this.isNotEmpty()) JsonArray(this) else null
