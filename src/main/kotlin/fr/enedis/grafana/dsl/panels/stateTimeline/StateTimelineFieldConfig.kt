package fr.enedis.grafana.dsl.panels.stateTimeline

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.fields.OverrideFieldConfig
import fr.enedis.grafana.dsl.panels.fields.OverridesFieldConfigBuilder
import fr.enedis.grafana.dsl.panels.stat.Mapping
import fr.enedis.grafana.dsl.panels.stat.MappingsBuilder
import org.json.JSONObject
import java.util.*

class StateTimelineFieldConfig(
    private val thresholds: Thresholds = Thresholds(),
    private val mappings: List<Mapping> = emptyList(),
    private val lineWidth: Int = 0,
    private val fillOpacity: Int = 70,
    private val spanNulls: Boolean = false,
    private val insertNulls: Boolean = false,
    private val colorScheme: ColorScheme? = null,
    private val overrides: List<OverrideFieldConfig> = emptyList()

) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "defaults" to jsonObject {
            "custom" to jsonObject {
                "lineWidth" to lineWidth
                "fillOpacity" to fillOpacity
                "spanNulls" to spanNulls
                "insertNulls" to insertNulls
                "hideFrom" to jsonObject {
                    "tooltip" to false
                    "viz" to false
                    "legend" to false
                }
            }
            "color" to colorScheme
            "thresholds" to thresholds
            "mappings" to JsonArray(mappings)
        }
        "overrides" to JsonArray(overrides)
    }
}

/**
 * Builder for StateTimelineFieldConfig
 * @author Anis JOLLY-LAARIF
 * @since 13.09.2024
 */
class StateTimelineFieldConfigBuilder {
    var thresholds: Thresholds = Thresholds()
    var mappings: List<Mapping> = emptyList()
    var lineWidth: Int = 0
    var fillOpacity: Int = 70
    var spanNulls: Boolean = false
    var insertNulls: Boolean = false
    var colorScheme: ColorScheme? = null
    var overrides: List<OverrideFieldConfig> = emptyList()
    internal fun createStateTimelineFieldConfig(): StateTimelineFieldConfig = StateTimelineFieldConfig(
        thresholds,
        mappings,
        lineWidth,
        fillOpacity,
        spanNulls,
        insertNulls,
        colorScheme,
        overrides
    )

    fun thresholds(mode: ThresholdMode = ThresholdMode.ABSOLUTE, build: ThresholdsBuilder.() -> Unit) {
        val builder = ThresholdsBuilder(mode)
        builder.build()
        thresholds = builder.createThresholds()
    }

    fun mappings(build: MappingsBuilder.() -> Unit) {
        val builder = MappingsBuilder()
        builder.build()
        mappings = builder.mappings
    }

    fun overrides(build: OverridesFieldConfigBuilder.() -> Unit) {
        val builder = OverridesFieldConfigBuilder()
        builder.build()
        overrides = builder.overrides
    }

}

class ColorScheme(private val mode: ColorSchemeMode, private val fixedColor: Color? = null) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode.scheme
        "fixedColor" to (fixedColor?.asString() ?: Color.RED)
    }
}

enum class ColorSchemeMode(val scheme: String) {
    FIXED("fixed"),
    SHADES("shades"),
    THRESHOLDS("thresholds"),
    RED_TO_GREEN("continuous-RdYlGr")
}
