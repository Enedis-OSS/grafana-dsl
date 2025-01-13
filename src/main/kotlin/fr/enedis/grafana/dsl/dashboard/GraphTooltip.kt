/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard

enum class GraphTooltip(val value: Int) {
    DEFAULT(0), SHARED_CROSSHAIR(1), SHARED_TOOLTIP(2)
}