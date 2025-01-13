/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table

/**
 * Builder for mappings tab
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
class MappingsBuilder {
    val mappings = mutableListOf<Mapping>()

    fun valueToText(build: ValueToTextMapping.Builder.() -> Unit) {
        val builder = ValueToTextMapping.Builder()
        builder.build()
        mappings.addAll(builder.valueToTexts)
    }

    fun rangeToText(build: RangeToTextMapping.Builder.() -> Unit) {
        val builder = RangeToTextMapping.Builder()
        builder.build()
        mappings.addAll(builder.rangeToTexts)
    }

    fun rangeToColor(build: RangeToColorMapping.Builder.() -> Unit) {
        val builder = RangeToColorMapping.Builder()
        builder.build()
        mappings.addAll(builder.rangeToColor)
    }
}