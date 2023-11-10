package fr.enedis.grafana.dsl.panels.transformation

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class PanelTransformationsBuilder {


    private var transformations: List<PanelTransformation> = mutableListOf()

    fun createPanelTransformations(): List<PanelTransformation> {
        return transformations;
    }

    /**
     * Add field from calculation
     * Use the row values to calculate new field
     */
    fun calculateField(build: CalculateFieldBuilder.() -> Unit) {
        val builder = CalculateFieldBuilder()
        builder.build()
        transformations += PanelTransformation(builder.id, builder.options)
    }

    fun organizeField(build: OrganizeFieldsBuilder.() -> Unit) {
        val builder = OrganizeFieldsBuilder()
        builder.build()
        transformations += PanelTransformation(builder.id, builder.options)
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

class OrganizeFieldsBuilder {
    var id = "organize"
    var options = OrganizeFieldsOptions()

    fun options(build: OrganizeFieldsOptions.() -> Unit) {
        val builder = OrganizeFieldsOptions()
        builder.build()
        options = builder
    }
}

abstract class TransformationOptionsBuilder : Json<JSONObject> {}
class CalculateFieldOptions(
    var mode: String? = "reduceRow",
    var fieldNames: Array<String>? = emptyArray(),
    var calculation: String? = null,
    var alias: String? = null,
    var replaceFields: Boolean? = true
) : TransformationOptionsBuilder() {

    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode
        "alias" to alias
        "reduce" to jsonObject {
            "reducer" to calculation
            "include" to fieldNames
        }
        "replaceFields" to replaceFields
    }
}

class OrganizeFieldsOptions(
    var excludeByName: Map<String, Boolean>? = emptyMap(),
    var indexByName: Map<String, Int>? = emptyMap(),
    var renameByName: Map<String, String>? = emptyMap()
) : TransformationOptionsBuilder() {
    override fun toJson(): JSONObject = jsonObject {
        "excludeByName" to excludeByName
        "indexByName" to indexByName
        "renameByName" to renameByName
    }
}