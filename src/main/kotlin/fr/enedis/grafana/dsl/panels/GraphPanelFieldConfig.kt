package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.stat.Mapping
import fr.enedis.grafana.dsl.panels.stat.MappingsBuilder

/**
 * Used to change how the data is displayed in visualizations
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class GraphPanelFieldConfig(
    private val unit: String = "none",
    private val min: Number? = null,
    private val max: Number? = null,
    private val decimals: Number? = null,
    private val noValue: String? = null,
    private val thresholds: Thresholds = Thresholds(),
    private val mappings: List<Mapping> = emptyList(),
    private val nullValueMode: NullValue = NullValue.NULL
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "defaults" to jsonObject {
            "unit" to unit
            "min" to min
            "max" to max
            "decimals" to decimals
            "noValue" to noValue
            "thresholds" to thresholds
            "mappings" to JsonArray(mappings)
            "nullValueMode" to nullValueMode.value
        }
    }
}

/**
 * Builder for GaugePanelFieldConfiguration
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class GraphPanelFieldConfigBuilder(private val nullValueMode: NullValue = NullValue.NULL) {
    var unit: String = "none"
    var min: Number? = null
    var max: Number? = null
    var decimals: Number? = null
    var noValue: String? = null
    var thresholds: Thresholds = Thresholds()
    var mappings: List<Mapping> = emptyList()

    internal fun createGraphPanelFieldConfig(): GraphPanelFieldConfig = GraphPanelFieldConfig(
        unit,
        min,
        max,
        decimals,
        noValue,
        thresholds,
        mappings,
        nullValueMode
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
}
