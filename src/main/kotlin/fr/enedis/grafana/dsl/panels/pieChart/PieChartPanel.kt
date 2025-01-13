/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.pieChart

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.TimeRange
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation

class PieChartPanel(
    private val basePanel: Panel,
    private val timerange: TimeRange = TimeRange(),
    private val repeat: Repeat? = null,
    private val fieldConfig: PieChartPanelFieldConfig = PieChartPanelFieldConfig(),
    private val options: PieChartPanelDisplayOptions = PieChartPanelDisplayOptions(),
    private val transformations: List<PanelTransformation> = emptyList(),
) : Panel {


    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "piechart"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
        "transformations" to JsonArray(transformations)
    }

}