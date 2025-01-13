/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Build columnStyle element
 * @author Aleksandr Korkin
 * @since 16.12.2019
 */
@DashboardElement
class ColumnStyleBuilder {
    val styles = mutableListOf<ColumnStyle>()
    var type: ColumnStyleType? = null
    var decimals: Int? = null
    var unit: DataUnit = DataUnit.SHORT
    var alias: String? = null
    var hidden: Boolean = false

    fun style(pattern: String, build: ColumnStyleBuilder.() -> Unit) {
        val builder = ColumnStyleBuilder()
        builder.build()
        styles += ColumnStyle(pattern = pattern, decimals = builder.decimals, unit = builder.unit, alias = builder.alias, type = builder.type)
    }
    fun rename(pair: Pair<String, String>) {
        styles += ColumnStyle(pattern = pair.first, alias = pair.second, unit = unit)
    }

    fun hide(pattern: String) {
        styles += ColumnStyle(pattern = pattern, unit = unit, hidden = true)
    }
}