package fr.enedis.grafana.dsl.panels.barGauge

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation

enum class DisplayMode(val value: String) {
    GRADIENT("gradient"), RETRO_LCD("lcd"), BASIC("basic")
}

/**
 * Options to refine visualization
 * @since 02.10.2022
 */
class BarGaugePanelDisplayOptions(
    private val reduceOptions: BarGaugePanelReduceOptions = BarGaugePanelReduceOptions(),
    private val orientation: Orientation = Orientation.AUTO,
    private val displayMode: DisplayMode = DisplayMode.GRADIENT,

    private val showUnfilled: Boolean = false,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "orientation" to orientation.value
        "displayMode" to displayMode.value
        "showUnfilled" to showUnfilled
    }
}

/**
 * Builder for BarGaugePanelDisplayOptions
 * @since 02.10.2022
 */
class BarGaugePanelDisplayOptionsBuilder {
    private var reduceOptions: BarGaugePanelReduceOptions = BarGaugePanelReduceOptions()
    var orientation: Orientation = Orientation.AUTO
    var displayMode: DisplayMode = DisplayMode.GRADIENT
    var showUnfilled: Boolean = false

    fun reduceOptions(build: GaugePanelReduceOptionsBuilder.() -> Unit) {
        val builder = GaugePanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun createBarGaugePanelDisplayOptions() = BarGaugePanelDisplayOptions(
        reduceOptions = reduceOptions,
        orientation = orientation,
        displayMode = displayMode,
        showUnfilled = showUnfilled
    )
}

/**
 * @since 02.10.2022
 */
class BarGaugePanelReduceOptions(
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
    fun createReduceOptions(): BarGaugePanelReduceOptions {
        return BarGaugePanelReduceOptions(fields = fields, values = values, calcs = calcs)
    }
}

