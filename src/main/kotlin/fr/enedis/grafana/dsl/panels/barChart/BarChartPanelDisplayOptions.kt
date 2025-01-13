/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.barChart


import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation

class BarChartPanelDisplayOptions(
    private val reduceOptions: BarChartPanelReduceOptions = BarChartPanelReduceOptions(),
    private val displayLabels: List<String> = emptyList<String>(),
    private val showValue: ShowValueMode = ShowValueMode.NEVER,
    private val stacking: StackingMode = StackingMode.NORMAL,
    private val xTickLabelSpacing: Int = 0,
    private val barWidth: Double = 0.5
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "displayLabels" to displayLabels
        "showValue" to showValue.value
        "stacking" to stacking.value
        "xTickLabelSpacing" to xTickLabelSpacing
        "barWidth" to barWidth
    }
}

class BarChartPanelDisplayOptionsBuilder {
    private var reduceOptions: BarChartPanelReduceOptions = BarChartPanelReduceOptions()
    var orientation: Orientation = Orientation.AUTO
    var displayLabels: List<String> = emptyList<String>()
    var showValue: ShowValueMode = ShowValueMode.NEVER
    var stacking: StackingMode = StackingMode.NORMAL
    var xTickLabelSpacing: Int = 0
    var barWidth: Double = 0.5

    fun reduceOptions(build: GaugePanelReduceOptionsBuilder.() -> Unit) {
        val builder = GaugePanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun createBarChartPanelDisplayOptions() = BarChartPanelDisplayOptions(
        reduceOptions = reduceOptions,
        displayLabels = displayLabels,
        showValue = showValue,
        stacking = stacking,
        xTickLabelSpacing = xTickLabelSpacing,
        barWidth = barWidth
    )
}

class BarChartPanelReduceOptions(
    private val values: Boolean = false,
    private val calcs: List<String> = listOf("lastNotNull"),
    private val fields: String = "",
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to values
        "calcs" to calcs
        "fields" to fields
    }
}

class GaugePanelReduceOptionsBuilder {
    var fields: String = ""
    var values: Boolean = false
    var calcs: List<String> = listOf("lastNotNull")
    fun createReduceOptions(): BarChartPanelReduceOptions {
        return BarChartPanelReduceOptions(fields = fields, values = values, calcs = calcs)
    }
}