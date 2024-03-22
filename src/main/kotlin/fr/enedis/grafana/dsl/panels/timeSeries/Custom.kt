package fr.enedis.grafana.dsl.panels.timeSeries

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.timeSeries.ConnectNullValue.THRESHOLD
import org.json.JSONObject

class Custom(
    private val drawStyle: DrawStyle = DrawStyle.LINE,
    private val lineInterpolation: LineInterpolation = LineInterpolation.LINEAR,
    private val barAlignment: BarAlignment = BarAlignment.CENTER,
    private val lineWidth: Int = 1,
    private val fillOpacity: Double = 20.0,
    private val gradientMode: GradientMode = GradientMode.NONE,
    private val spanNulls: SpanNulls = SpanNulls(),
    private val insertNulls: SpanNulls = SpanNulls(), //ALWAYS is not available for this property
    private val showPoints: ShowPointsMode = ShowPointsMode.AUTO,
    private val pointSize: Int = 5,
    private val stacking: Stacking = Stacking(),
    private val axisPlacement: AxisPlacement = AxisPlacement.AUTO,
    private val axisLabel: String = "",
    private val axisColorMode: AxisColorMode = AxisColorMode.TEXT,
    private val axisBorderShow: Boolean = false,
    private val scaleDistribution: ScaleDistribution = ScaleDistribution(),
    private val axisCenteredZero: Boolean = false,
    private val hideFrom: HideFrom = HideFrom(),
    private val thresholdsStyle: ThresholdsStyle = ThresholdsStyle(),
    private val lineStyle: LineStyle? = null,
    private val axisSoftMin: Int? = null,
    private val axisSoftMax: Int? = null,
    private val axisWidth: Int? = null,
    private val axisGridShow: Boolean? = null,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "drawStyle" to drawStyle.value
        "lineInterpolation" to lineInterpolation.value
        "barAlignment" to barAlignment.value
        "lineWidth" to lineWidth
        "fillOpacity" to fillOpacity
        "gradientMode" to gradientMode.value
        "spanNulls" to spanNulls.value()
        "insertNulls" to insertNulls.value()
        "showPoints" to showPoints.value
        "pointSize" to pointSize
        "stacking" to stacking
        "axisPlacement" to axisPlacement.value
        "axisLabel" to axisLabel
        "axisColorMode" to axisColorMode.value
        "axisBorderShow" to axisBorderShow
        "scaleDistribution" to scaleDistribution
        "axisCenteredZero" to axisCenteredZero
        "hideFrom" to hideFrom
        "thresholdsStyle" to thresholdsStyle
        "lineStyle" to lineStyle
        "axisSoftMin" to axisSoftMin
        "axisSoftMax" to axisSoftMax
        "axisWidth" to axisWidth
        "axisGridShow" to axisGridShow
    }
}

data class SpanNulls(
    val connectNullValue: ConnectNullValue = ConnectNullValue.NEVER,
    val threshold: Long? = 3600000
) {
    fun value() = if (connectNullValue == THRESHOLD) threshold else connectNullValue.value
}

data class HideFrom(
    val tooltip: Boolean = false,
    val viz: Boolean = false,
    val legend: Boolean = false,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "tooltip" to tooltip
        "viz" to viz
        "legend" to legend
    }
}

data class ScaleDistribution(
    val type: ScaleDistributionMode = ScaleDistributionMode.LINEAR,
    val log: Int? = null
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "type" to type.value
        "log" to log
    }
}

data class Stacking(
    val mode: StackingMode = StackingMode.NONE, val group: String = "A"
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode.value
        "group" to group
    }
}

data class ThresholdsStyle(
    val mode: ThresholdsStyleMode = ThresholdsStyleMode.OFF
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode.value
    }
}

data class LineStyle(
    val fill: LineStyleFill? = null,
    val dash: List<Int>? = null,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "fill" to fill?.value
        "dash" to dash
    }
}

enum class DrawStyle(val value: String) {
    LINE("line"),
    BARS("bars"),
    POINTS("points"),
}

enum class LineInterpolation(val value: String) {
    LINEAR("linear"),
    SMOOTH("smooth"),
    STEP_BEFORE("stepBefore"),
    STEP_AFTER("stepAfter"),
}

enum class BarAlignment(val value: Int) {
    BEFORE(-1),
    CENTER(0),
    AFTER(1),
}

enum class GradientMode(val value: String) {
    HUE("hue"),
    NONE("none"),
    OPACITY("opacity"),
    SCHEME("scheme"),
}

enum class ConnectNullValue(val value: Boolean?) {
    NEVER(false), ALWAYS(true), THRESHOLD(null)
}

enum class ShowPointsMode(val value: String) {
    NEVER("never"),
    AUTO("auto"),
    ALWAYS("always"),
}

enum class AxisPlacement(val value: String) {
    AUTO("auto"),
    BOTTOM("bottom"),
    HIDDEN("hidden"),
    LEFT("left"),
    RIGHT("right"),
    TOP("top"),
}

enum class AxisColorMode(val value: String) {
    SERIES("series"),
    TEXT("text"),
}

enum class StackingMode(val value: String) {
    NONE("none"),
    NORMAL("normal"),
    PERCENT("percent"),
}

enum class ScaleDistributionMode(val value: String) {
    LINEAR("linear"),
    LOG("log"),
    ORDINAL("ordinal"),
    SYMLOG("symlog"),
}

enum class ThresholdsStyleMode(val value: String) {
    AREA("area"),
    DASHED("dashed"),
    DASHED_AREA("dashed+area"),
    LINE("line"),
    LINE_AREA("line+area"),
    OFF("off"),
    SERIES("series"),
}

enum class LineStyleFill(val value: String) {
    DASH("dash"),
    DOT("dot"),
    SOLID("solid"),
    SQUARE("square"),
}