package fr.enedis.grafana.dsl.panels.stat

/**
 * Builder for mappings tab
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
class OverrideFieldConfigBuilder {
    val overrides = mutableListOf<OverrideFieldConfig>()

    fun byName(name: String, build: PropertyFieldConfigBuilder.() -> Unit) {
        val builder = PropertyFieldConfigBuilder()
        builder.build()
        overrides.add(
            OverrideFieldConfig(
                matcher = MatcherFieldConfig("byName", name),
                properties = builder.properties
            )
        )
    }

    fun byQuery(name: String, build: PropertyFieldConfigBuilder.() -> Unit) {
        val builder = PropertyFieldConfigBuilder()
        builder.build()
        overrides.add(
            OverrideFieldConfig(
                matcher = MatcherFieldConfig("byFrameRefID", name),
                properties = builder.properties
            )
        )
    }
}
