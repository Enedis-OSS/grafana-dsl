/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.transformation

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

class PanelTransformation(
    private val id: String,
    private val options: TransformationOptionsBuilder,
    private var filter: PanelTransformationFilter? = null,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "filter" to filter
        "options" to options
    }
}