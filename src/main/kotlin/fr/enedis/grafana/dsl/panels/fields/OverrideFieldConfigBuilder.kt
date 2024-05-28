package fr.enedis.grafana.dsl.panels.fields

import fr.enedis.grafana.dsl.panels.stat.PropertiesFieldConfigBuilder
import fr.enedis.grafana.dsl.panels.stat.PropertyFieldConfigBuilder

/**
 * Builder for mappings tab
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
class OverridesFieldConfigBuilder {
    val overrides = mutableListOf<OverrideFieldConfig>()

    fun byName(name: String, build: PropertiesFieldConfigBuilder.() -> Unit) {
        filter("byName", name, build)
    }

    fun byQuery(name: String, build: PropertiesFieldConfigBuilder.() -> Unit) {
        filter("byFrameRefID", name, build)
    }

    fun byRegexp(regexp: String, build: PropertiesFieldConfigBuilder.() -> Unit) {
        filter("byRegexp", regexp, build)
    }

    fun byType(type: String, build: PropertiesFieldConfigBuilder.() -> Unit) {
        filter("byType", type, build)
    }

    fun byValue(value: Map<String, Any>, build: PropertiesFieldConfigBuilder.() -> Unit) {
        filter("byValue", value, build)
    }

    fun override(build: OverrideFieldConfigBuilder.() -> Unit) {
        val builder = OverrideFieldConfigBuilder()
        builder.build()
        overrides.add(builder.create())
    }

    private fun filter(
        matcher: String,
        options: Any,
        build: PropertiesFieldConfigBuilder.() -> Unit
    ) {
        val builder = PropertiesFieldConfigBuilder()
        builder.build()
        overrides.add(
            OverrideFieldConfig(
                matcher = MatcherFieldConfig(matcher, options),
                properties = builder.properties
            )
        )
    }
}

class OverrideFieldConfigBuilder {
    var matcher: MatcherFieldConfig = MatcherFieldConfig("byName", "")
    var properties = mutableListOf<PropertyFieldConfig>()


    fun create() = OverrideFieldConfig(
        matcher = matcher,
        properties = properties
    )

    fun matcher(build: MatcherFieldConfigBuilder.() -> Unit) {
        val builder = MatcherFieldConfigBuilder()
        builder.build()
        matcher = builder.create()
    }

    fun properties(build: PropertyFieldConfigBuilder.() -> Unit) {
        val builder = PropertyFieldConfigBuilder()
        builder.build()
        properties.add(builder.create())
    }
}


class MatcherFieldConfigBuilder {
    lateinit var id: String
    lateinit var options: String
    fun create(): MatcherFieldConfig = MatcherFieldConfig(
        id = id,
        options = options
    )
}