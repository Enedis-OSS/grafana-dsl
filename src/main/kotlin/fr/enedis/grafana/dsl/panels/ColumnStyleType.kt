/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

enum class ColumnStyleType(val type: String) {
    NUMBER("number"),
    STRING("string"),
    DATE("date"),
    HIDDEN("hidden")

}