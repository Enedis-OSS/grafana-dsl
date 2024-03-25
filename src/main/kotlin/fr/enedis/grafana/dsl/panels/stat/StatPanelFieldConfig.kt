package fr.enedis.grafana.dsl.panels.stat

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.NullValue
import fr.enedis.grafana.dsl.panels.ThresholdMode
import fr.enedis.grafana.dsl.panels.Thresholds
import fr.enedis.grafana.dsl.panels.ThresholdsBuilder

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
    private val decimals: String? = null,
    private val noValue: String? = null,
    private val overrides: List<OverrideFieldConfig> = emptyList()

) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "defaults" to jsonObject {
            "unit" to unit
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
    var decimals: String? = null
    var noValue: String? = null
    var overrides: List<OverrideFieldConfig> = emptyList()
    internal fun createStatPanelFieldConfig(): StatPanelFieldConfig = StatPanelFieldConfig(
        displayName,
        thresholds,
        mappings,
        nullValueMode,
        unit,
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

class OverrideFieldConfig(
    private val matcher: MatcherFieldConfig,
    private val properties: List<PropertyFieldConfig>
) : Json<JSONObject> {
    override fun toJson(): JSONObject  = jsonObject {
        "matcher" to matcher
        "properties" to JsonArray(properties)
    }
}

class MatcherFieldConfig(
    private val id : String,
    private val options: String
): Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "id" to id
        "options" to options
    }
}

class PropertyFieldConfig(
    private val id : String,
    private val value: Any
): Json<JSONObject> {
    override fun toJson(): JSONObject  = jsonObject {
        "id" to id
        "value" to value
    }
}