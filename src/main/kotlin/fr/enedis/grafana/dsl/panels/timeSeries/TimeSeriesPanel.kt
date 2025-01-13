/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.timeSeries

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Legend
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.TimeRange
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation
import fr.enedis.grafana.dsl.time.Duration

class TimeSeriesPanel(private val basePanel: Panel,
                      private val timeRange: TimeRange = TimeRange(),
                      private val repeat: Repeat? = null,
                      private val fieldConfig: TimeSeriesPanelFieldConfig = TimeSeriesPanelFieldConfig(),
                      private val options: TimeSeriesPanelDisplayOptions = TimeSeriesPanelDisplayOptions(),
                      private val transformations: List<PanelTransformation> = emptyList(),
                      private val interval: Duration? = null,
                      private val legend: Legend = Legend.EMPTY,
) : Panel {


    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "timeseries"
        embed(timeRange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
        "transformations" to JsonArray(transformations)
        "interval" to interval
    }

}