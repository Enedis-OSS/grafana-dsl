package fr.enedis.grafana.dsl.panels.stat

class PropertiesFieldConfigBuilder {
    val properties = mutableListOf<PropertyFieldConfig>()

    fun displayName(newValue: String) {
        properties.add(
            PropertyFieldConfig(
                id = "displayName",
                value = newValue
            )
        )
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