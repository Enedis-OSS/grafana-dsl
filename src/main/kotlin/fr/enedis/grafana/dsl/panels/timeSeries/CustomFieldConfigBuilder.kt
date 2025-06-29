/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.timeSeries

class CustomFieldConfigBuilder {
    var drawStyle: DrawStyle = DrawStyle.LINE
    var lineInterpolation: LineInterpolation = LineInterpolation.LINEAR
    var barAlignment: BarAlignment = BarAlignment.CENTER
    var lineWidth: Int = 1
    var fillOpacity: Double = 20.0
    var gradientMode: GradientMode = GradientMode.NONE
    var spanNulls: SpanNulls = SpanNulls()
    var insertNulls: SpanNulls = SpanNulls()
    var showPoints: ShowPointsMode = ShowPointsMode.NEVER
    var pointSize: Int = 5
    var stacking: Stacking = Stacking()
    var axisPlacement: AxisPlacement = AxisPlacement.AUTO
    var axisLabel: String = ""
    var axisColorMode: AxisColorMode = AxisColorMode.TEXT
    var axisBorderShow: Boolean = false
    var scaleDistribution: ScaleDistribution = ScaleDistribution()
    var axisCenteredZero: Boolean = false
    var hideFrom: HideFrom = HideFrom()
    var thresholdsStyle: ThresholdsStyle = ThresholdsStyle()
    var lineStyle: LineStyle? = null
    var axisSoftMin: Int? = null
    var axisSoftMax: Int? = null

    fun createCustom() = CustomFieldConfig(
        drawStyle = drawStyle,
        lineInterpolation = lineInterpolation,
        barAlignment = barAlignment,
        lineWidth = lineWidth,
        fillOpacity = fillOpacity,
        gradientMode = gradientMode,
        spanNulls = spanNulls,
        insertNulls = insertNulls,
        showPoints = showPoints,
        pointSize = pointSize,
        stacking = stacking,
        axisPlacement = axisPlacement,
        axisLabel = axisLabel,
        axisColorMode = axisColorMode,
        axisBorderShow = axisBorderShow,
        scaleDistribution = scaleDistribution,
        axisCenteredZero = axisCenteredZero,
        hideFrom = hideFrom,
        thresholdsStyle = thresholdsStyle,
        lineStyle = lineStyle,
        axisSoftMin = axisSoftMin,
        axisSoftMax = axisSoftMax
    )
}