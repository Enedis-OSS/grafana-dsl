/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.stateTimeline

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject


/**
 * Options to refine visualization
 * @author Anis JOLLY-LAARIF
 * @since 12.09.2024
 */
class StateTimelineDisplayOptions(
    private val mergeValues: MergeValue = MergeValue.FALSE,
    private val showValue: ShowValue = ShowValue.AUTO,
    private val alignValue: AlignValue = AlignValue.LEFT,
    private val legend: StateTimelineLegend = StateTimelineLegend(),
    private val tooltip: StateTimelineTooltip = StateTimelineTooltip(),
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mergeValues" to mergeValues.name.lowercase()
        "showValue" to showValue.name.lowercase()
        "alignValue" to alignValue.name.lowercase()
        "legend" to legend
        "tooltip" to tooltip
    }
}

/**
 * Builder for StateTimelineDisplayOptions
 * @author Anis JOLLY-LAARIF
 * @since 13.09.2024
 */
class StatPanelDisplayOptionsBuilder {
    var mergeValues: MergeValue = MergeValue.FALSE
    var showValue: ShowValue = ShowValue.AUTO
    var alignValue: AlignValue = AlignValue.LEFT
    var legend: StateTimelineLegend = StateTimelineLegend()
    var tooltip: StateTimelineTooltip = StateTimelineTooltip()

    fun legend(build: StateTimelineLegendBuilder.() -> Unit) {
        val builder = StateTimelineLegendBuilder()
        builder.build()
        legend = builder.createStateTimelineLegend()
    }

    fun tooltip(build: StateTimelineTooltipBuilder.() -> Unit) {
        val builder = StateTimelineTooltipBuilder()
        builder.build()
        tooltip = builder.createStateTimelineTooltip()
    }

    fun createStatPanelDisplayOptions() = StateTimelineDisplayOptions(
        mergeValues = mergeValues,
        showValue = showValue,
        alignValue = alignValue,
        legend = legend,
        tooltip = tooltip
    )
}


/**
 * @author Anis JOLLY-LAARIF
 * @since 12.09.2024
 */
class StateTimelineLegend(
    private val showLegend: String = "false",
    private val displayMode: String = "list",
    private val placement: String = "bottom"
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to showLegend
        "sort" to displayMode
        "sort" to placement
    }
}

class StateTimelineLegendBuilder {
    var showLegend: String = "false"
    var displayMode: String = "list"
    var placement: String = "bottom"
    fun createStateTimelineLegend(): StateTimelineLegend {
        return StateTimelineLegend(showLegend = showLegend, displayMode = displayMode, placement = placement)
    }
}


/**
 * @author Anis JOLLY-LAARIF
 * @since 12.09.2024
 */
class StateTimelineTooltip(
    private val mode: String = "single",
    private val sort: String = "none",
    private val maxWidth: Int = 0,
    private val maxHeight: Int = 0
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode
        "sort" to sort
        "maxWidth" to maxWidth
        "maxHeight" to maxHeight
    }
}

class StateTimelineTooltipBuilder {
    var mode: String = "single"
    var sort: String = "none"
    var maxWidth: Int = 0
    var maxHeight: Int = 0
    fun createStateTimelineTooltip(): StateTimelineTooltip {
        return StateTimelineTooltip(mode = mode, sort = sort, maxWidth = maxWidth, maxHeight = maxHeight)
    }
}


enum class AlignValue {
    LEFT, CENTER, RIGHT
}

enum class ShowValue {
    AUTO, ALWAYS, NEVER
}

enum class MergeValue {
    FALSE, TRUE
}