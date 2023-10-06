package fr.enedis.grafana.dsl.panels.stat

class StatPanelTransformationsBuilder {


    private var transformations: List<StatPanelTransformation> = mutableListOf()

    fun createStatPanelTransformations(): List<StatPanelTransformation> {
        return transformations;
    }

    /**
     * Add field from calculation
     * Use the row values to calculate new field
     */
    fun calculateField(build: CalculateFieldBuilder.() -> Unit) {
        val builder = CalculateFieldBuilder()
        builder.build()
        transformations += StatPanelTransformation(builder.id, builder.options)
    }


}

class CalculateFieldBuilder {
    var id = "calculateField"
    var options = CalculateFieldOptions()

    fun options(build: CalculateFieldOptions.() -> Unit) {
        val builder = CalculateFieldOptions()
        builder.build()
        options = builder
    }
}

class CalculateFieldOptions {
    var mode: String? = "reduceRow"
    var fieldName: String? = null
    var calculation: String? = null
    var alias: String? = null
    var replaceFields: Boolean = true
}
