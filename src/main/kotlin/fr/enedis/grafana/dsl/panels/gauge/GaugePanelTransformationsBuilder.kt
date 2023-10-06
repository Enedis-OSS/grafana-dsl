package fr.enedis.grafana.dsl.panels.gauge

class GaugePanelTransformationsBuilder {


    private var transformations: List<GaugePanelTransformation> = mutableListOf()

    fun createGaugePanelTransformations(): List<GaugePanelTransformation> {
        return transformations;
    }

    /**
     * Add field from calculation
     * Use the row values to calculate new field
     */
    fun calculateField(build: CalculateFieldBuilder.() -> Unit) {
        val builder = CalculateFieldBuilder()
        builder.build()
        transformations += GaugePanelTransformation(builder.id, builder.options)
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
