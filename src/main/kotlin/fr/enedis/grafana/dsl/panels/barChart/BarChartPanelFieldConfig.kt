/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.barChart


import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.NullValue
import fr.enedis.grafana.dsl.panels.ThresholdMode
import fr.enedis.grafana.dsl.panels.Thresholds
import fr.enedis.grafana.dsl.panels.ThresholdsBuilder
import fr.enedis.grafana.dsl.panels.gauge.Mapping
import fr.enedis.grafana.dsl.panels.gauge.MappingsBuilder
import fr.enedis.grafana.dsl.panels.fields.OverrideFieldConfig
import fr.enedis.grafana.dsl.panels.fields.OverridesFieldConfigBuilder
import fr.enedis.grafana.dsl.panels.stateTimeline.ColorScheme
import fr.enedis.grafana.dsl.panels.timeSeries.CustomFieldConfig
import fr.enedis.grafana.dsl.panels.timeSeries.CustomFieldConfigBuilder
import org.json.JSONObject

/**
 * Used to change how the data is displayed in visualizations
 * @since 02.10.2022
 */
class BarChartPanelFieldConfig(
    private val unit: String = "none",
    private val min: Number? = null,
    private val max: Number? = null,
    private val decimals: Number? = null,
    private val noValue: String? = null,
    private val thresholds: Thresholds = Thresholds(),
    private val mappings: List<Mapping> = emptyList(),
    private val nullValueMode: NullValue = NullValue.NULL,
    private val overrides: List<OverrideFieldConfig> = emptyList(),
    private val custom: CustomFieldConfig = CustomFieldConfig(),
    private val colorScheme: ColorScheme? = null,
    private val displayName: String? = null,

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
            "custom" to custom
            "color" to colorScheme
            "displayName" to displayName

        }
        "overrides" to JsonArray(overrides)
    }
}

class BarChartPanelFieldConfigBuilder(private val nullValueMode: NullValue = NullValue.NULL) {
    var unit: String = "none"
    var min: Number? = null
    var max: Number? = null
    var decimals: Number? = null
    var noValue: String? = null
    var thresholds: Thresholds = Thresholds()
    var mappings: List<Mapping> = emptyList()
    var overrides: List<OverrideFieldConfig> = emptyList()
    var custom: CustomFieldConfig = CustomFieldConfig()
    var colorScheme: ColorScheme? = null
    var displayName: String? = null

    internal fun createBarChartPanelFieldConfig(): BarChartPanelFieldConfig = BarChartPanelFieldConfig(
        unit,
        min,
        max,
        decimals,
        noValue,
        thresholds,
        mappings,
        nullValueMode,
        overrides,
        custom,
        colorScheme,
        displayName
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

    fun custom(build: CustomFieldConfigBuilder.() -> Unit) {
        val builder = CustomFieldConfigBuilder()
        builder.build()
        custom = builder.createCustom()
    }
}
