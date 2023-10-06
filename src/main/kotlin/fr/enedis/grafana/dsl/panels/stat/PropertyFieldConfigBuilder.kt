package fr.enedis.grafana.dsl.panels.stat

class PropertyFieldConfigBuilder {

    val properties = mutableListOf<PropertyFieldConfig>()

    fun displayName(newValue: String) {
        properties.add(
            PropertyFieldConfig(
                id = "displayName",
                value = newValue
            )
        )
    }
}