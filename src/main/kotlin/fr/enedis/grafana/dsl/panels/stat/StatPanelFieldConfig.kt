package fr.enedis.grafana.dsl.panels.stat

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.fields.OverrideFieldConfig
import fr.enedis.grafana.dsl.panels.fields.OverridesFieldConfigBuilder
import java.util.*

/**
 * Used to change how the data is displayed in visualizations
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelFieldConfig(
    private val displayName: String? = null,
    private val thresholds: Thresholds = Thresholds(),
    private val mappings: List<Mapping> = emptyList(),
    private val nullValueMode: NullValue = NullValue.NULL,
    private val unit: String = "none",
    private val colorScheme: ColorScheme? = null,
    private val decimals: String? = null,
    private val noValue: String? = null,
    private val overrides: List<OverrideFieldConfig> = emptyList()

) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "defaults" to jsonObject {
            "unit" to unit
            "color" to colorScheme
            "decimals" to decimals
            "noValue" to noValue
            "displayName" to displayName
            "thresholds" to thresholds
            "mappings" to JsonArray(mappings)
            "nullValueMode" to nullValueMode.value
        }
        "overrides" to JsonArray(overrides)
    }
}

/**
 * Builder for StatPanelFieldConfiguration
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelFieldConfigBuilder(private val nullValueMode: NullValue = NullValue.NULL) {
    var displayName: String? = null
    var thresholds: Thresholds = Thresholds()
    var mappings: List<Mapping> = emptyList()
    var unit: String = "none"
    var colorScheme: ColorScheme? = null
    var decimals: String? = null
    var noValue: String? = null
    var overrides: List<OverrideFieldConfig> = emptyList()
    internal fun createStatPanelFieldConfig(): StatPanelFieldConfig = StatPanelFieldConfig(
        displayName,
        thresholds,
        mappings,
        nullValueMode,
        unit,
        colorScheme,
        decimals,
        noValue,
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

class ColorScheme(private val mode: ColorSchemeMode, private val fixedColor: Color) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode.name.lowercase(Locale.getDefault())
        "fixedColor" to fixedColor.asString()
    }
}

enum class ColorSchemeMode {
    FIXED, SHADES, THRESHOLDS
}