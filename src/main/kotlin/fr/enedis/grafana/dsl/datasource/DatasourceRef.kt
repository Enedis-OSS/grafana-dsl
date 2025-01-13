/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.datasource

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

/**
 * Datasource reference is used to reference a datasource from panels, queries, etc.
 * @property type  describes the type of the datasource, like "prometheus", "graphite", etc.
 * @property uid   unique identifier of the datasource
 */
data class DatasourceRef(val type: String, val uid: String) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "type" to type
        "uid" to uid
    }
}