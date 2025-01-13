/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.transformation

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class PanelTransformationFilter(
    private val id: String = "",
    private val options: String = ""
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "options" to options
    }
}