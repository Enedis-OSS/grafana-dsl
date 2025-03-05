/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table


import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject


class TablePanelDisplayOptions(
    private val cellHeight: String? = null,
    private val footer: Any = mapOf<String, Any>(),
    private val showHeader: Boolean? = false,

    ) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "cellHeight" to cellHeight
        "footer" to footer
        "showHeader" to showHeader

    }
}

class TablePanelDisplayOptionsBuilder() {
    var cellHeight: String? = null
    var footer: Any = mapOf<String, Any>()
    var showHeader: Boolean? = false

    fun createTablePanelDisplayOptions() = TablePanelDisplayOptions(
        cellHeight = cellHeight,
        footer = footer,
        showHeader = showHeader,
    )

}



