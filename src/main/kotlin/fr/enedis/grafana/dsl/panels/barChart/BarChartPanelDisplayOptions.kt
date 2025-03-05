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
import fr.enedis.grafana.dsl.panels.Legend
import fr.enedis.grafana.dsl.panels.LegendBuilder
import fr.enedis.grafana.dsl.panels.Orientation
import fr.enedis.grafana.dsl.panels.timeSeries.TimeSeriesLegend
import fr.enedis.grafana.dsl.panels.timeSeries.TimeSeriesLegendBuilder
import fr.enedis.grafana.dsl.panels.timeSeries.TimeSeriesTooltip

class BarChartPanelDisplayOptions(
    private val reduceOptions: BarChartPanelReduceOptions = BarChartPanelReduceOptions(),
    private val displayLabels: List<String> = emptyList<String>(),
    private val showValue: ShowValueMode = ShowValueMode.NEVER,
    private val stacking: StackingMode = StackingMode.NORMAL,
    private val xTickLabelSpacing: Int = 0,
    private val barWidth: Double = 0.97,
    private val xField: String? = null,
    private val xTickLabelRotation: Int = 0,
    private val groupWidth: Double = 0.7,
    private val barRadius: Int = 0,
    private val colorByField: String? = null,
    private val fullHighlight: Boolean = false,
    private val legend: TimeSeriesLegend = TimeSeriesLegend.DEFAULT,
    private val tooltip: TimeSeriesTooltip = TimeSeriesTooltip(),
    private val orientation: Orientation? = Orientation.AUTO


) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "displayLabels" to displayLabels
        "showValue" to showValue.value
        "stacking" to stacking.value
        "xTickLabelSpacing" to xTickLabelSpacing
        "barWidth" to barWidth
        "xField" to barWidth
        "xTickLabelRotation" to xTickLabelRotation
        "groupWidth" to groupWidth
        "barRadius" to barRadius
        "colorByField" to colorByField
        "fullHighlight" to fullHighlight
        "legend" to legend
        "tooltip" to tooltip
        "xField" to xField
        "orientation" to orientation?.value
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
    var xField: String? = null
    var xTickLabelRotation: Int = 0
    var groupWidth: Double = 0.0
    var barRadius: Int = 0
    var colorByField: String? = null
    var fullHighlight: Boolean = false
    var legend: TimeSeriesLegend = TimeSeriesLegend.DEFAULT
    var tooltip: TimeSeriesTooltip = TimeSeriesTooltip()

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
        barWidth = barWidth,
        xField = xField,
        xTickLabelRotation = xTickLabelRotation,
        groupWidth = groupWidth,
        barRadius = barRadius,
        colorByField = colorByField,
        fullHighlight = fullHighlight,
        legend = legend,
        tooltip = tooltip,
        orientation = orientation,

        )

    fun legend(build: TimeSeriesLegendBuilder.() -> Unit) {
        val builder = TimeSeriesLegendBuilder()
        builder.build()
        legend = builder.createTimeSeriesLegend()
    }
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
