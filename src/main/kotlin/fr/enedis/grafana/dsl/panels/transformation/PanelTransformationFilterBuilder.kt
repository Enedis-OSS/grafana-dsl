/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.transformation

class PanelTransformationFilterBuilder {
    var id: String = ""
    var options: String = ""

    fun createPanelTransformationFilter() : PanelTransformationFilter = PanelTransformationFilter(id=id, options=options)
}