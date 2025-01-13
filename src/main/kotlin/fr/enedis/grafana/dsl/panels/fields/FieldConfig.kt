/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.fields

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class OverrideFieldConfig(
    private val matcher: MatcherFieldConfig,
    private val properties: List<PropertyFieldConfig>
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "matcher" to matcher
        "properties" to JsonArray(properties)
    }
}

class MatcherFieldConfig(
    private val id: String,
    private val options: Any
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "options" to options
    }
}

class PropertyFieldConfig(
    private val id: String,
    private val value: Any
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "value" to value
    }
}