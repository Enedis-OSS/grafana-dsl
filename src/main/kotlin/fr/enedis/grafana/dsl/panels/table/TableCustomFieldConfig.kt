/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class TableCustomFieldConfig(
    private val align: String = "auto",
    private val inspect: Boolean = false,
    private val cellOptionsType: String = "auto",
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "align" to align
        "inspect" to inspect
        "cellOptions" to jsonObject {
            "type" to cellOptionsType
        }
    }
}