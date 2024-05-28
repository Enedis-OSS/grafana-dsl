package fr.enedis.grafana.dsl.panels.table

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.ThresholdMode
import fr.enedis.grafana.dsl.panels.Thresholds
import fr.enedis.grafana.dsl.panels.ThresholdsBuilder
import fr.enedis.grafana.dsl.panels.fields.OverrideFieldConfig
import fr.enedis.grafana.dsl.panels.fields.OverridesFieldConfigBuilder
import fr.enedis.grafana.dsl.panels.stat.Mapping
import fr.enedis.grafana.dsl.panels.stat.MappingsBuilder
import org.json.JSONObject

class TablePanelFieldConfig(
    private val thresholds: Thresholds = Thresholds(),
    private val mappings: List<Mapping> = emptyList(),
    private val overrides: List<OverrideFieldConfig> = emptyList(),
    private val custom: TableCustomFieldConfig = TableCustomFieldConfig()
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "defaults" to jsonObject {
            "thresholds" to thresholds
            "mappings" to JsonArray(mappings)
            "custom" to custom
        }
        "overrides" to JsonArray(overrides)
    }
}

class TablePanelFieldConfigBuilder() {
    var thresholds: Thresholds = Thresholds()
    var mappings: List<Mapping> = emptyList()
    var overrides: List<OverrideFieldConfig> = emptyList()
    var custom: TableCustomFieldConfig = TableCustomFieldConfig()
    internal fun createTablePanelFieldConfig(): TablePanelFieldConfig = TablePanelFieldConfig(
        thresholds,
        mappings,
        overrides,
        custom
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

    fun custom(build: TableCustomFieldConfigBuilder.() -> Unit) {
        val builder = TableCustomFieldConfigBuilder()
        builder.build()
        custom = builder.createCustom()
    }
}
