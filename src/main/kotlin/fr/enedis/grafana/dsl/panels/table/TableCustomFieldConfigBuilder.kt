/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table

class TableCustomFieldConfigBuilder {
    var align: String = "auto"
    var inspect: Boolean = false
    var cellOptionsType: String = "auto"

    fun createCustom() = TableCustomFieldConfig(
        align = align,
        inspect = inspect,
        cellOptionsType = cellOptionsType
    )
}