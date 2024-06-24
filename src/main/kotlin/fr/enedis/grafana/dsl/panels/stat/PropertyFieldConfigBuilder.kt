package fr.enedis.grafana.dsl.panels.stat

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Color
import fr.enedis.grafana.dsl.panels.fields.PropertyFieldConfig

class PropertiesFieldConfigBuilder {
    val properties = mutableListOf<PropertyFieldConfig>()

    fun displayName(newValue: String) {
        properties {
            id = "displayName"
            value = newValue
        }
    }

    fun colorScheme(mode: FieldColorMode = FieldColorMode.FIXED, color: Color, seriesBy: ColorSeriesBy? = null) {
        properties {
            id = "color"
            value = mapOf(
                "fixedColor" to color.asRgbaString(),
                "mode" to mode.value,
                "seriesBy" to seriesBy?.value,
            )
        }
    }

    fun hide() {
        properties {
            id = "custom.hidden"
            value = true
        }
    }

    fun valueMapping(vararg mappings: Pair<String, String>) {
        properties {
            id = "mappings"
            value = listOf(
                jsonObject {
                    "type" to "value"
                    "options" to jsonObject {
                        mappings.map {
                            it.first to jsonObject {
                                "text" to it.second
                            }
                        }
                    }
                }
            )
        }
    }

    fun properties(build: PropertyBuilder.() -> Unit) {
        val builder = PropertyBuilder()
        builder.build()
        properties.add(
            PropertyFieldConfig(
                id = builder.id,
                value = builder.value
            )
        )
    }
}

class PropertyFieldConfigBuilder {
    var id: String = ""
    var value: Any = mapOf<String, Any>()

    fun create() = PropertyFieldConfig(
        id = id,
        value = value
    )
}

class PropertyBuilder {
    var id: String = ""
    var value: Any = mapOf<String, Any>()
}

enum class FieldColorMode(val value: String) {
    THRESHOLDS("thresholds"),
    PALETTE_CLASSIC("palette-classic"),
    PALETTE_CLASSIC_BY_NAME("palette-classic-by-name"),
    CONTINUOUS_GR_YL_RD("continuous-GrYlRd"),
    CONTINUOUS_RD_YL_GR("continuous-RdYlGr"),
    CONTINUOUS_BL_YL_RD("continuous-BlYlRd"),
    CONTINUOUS_YL_RD("continuous-YlRd"),
    CONTINUOUS_BL_PU("continuous-BlPu"),
    CONTINUOUS_YL_BL("continuous-YlBl"),
    CONTINUOUS_BLUES("continuous-blues"),
    CONTINUOUS_REDS("continuous-reds"),
    CONTINUOUS_GREENS("continuous-greens"),
    CONTINUOUS_PURPLES("continuous-purples"),
    FIXED("fixed"),
    SHADES("shades"),
}

enum class ColorSeriesBy(val value: String) {
    MAX("max"),
    MIN("min"),
    LAST("last"),
}