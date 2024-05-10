package fr.enedis.grafana.dsl.panels.transformation

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONArray
import org.json.JSONObject

class PanelTransformationsBuilder {


    private var transformations: List<PanelTransformation> = mutableListOf()

    fun createPanelTransformations(): List<PanelTransformation> {
        return transformations
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

    fun sortBy(build: SortByBuilder.() -> Unit) {
        val builder = SortByBuilder()
        builder.build()
        transformations += PanelTransformation(builder.id, builder.options)
    }

    fun filterFieldsByName(build: FilterFieldsByNameBuilder.() -> Unit) {
        val builder = FilterFieldsByNameBuilder()
        builder.build()
        transformations += PanelTransformation(builder.id, builder.options)
    }

    fun groupBy(build: GroupByBuilder.() -> Unit) {
        val builder = GroupByBuilder()
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

class GroupByBuilder {
    var id = "groupBy"
    var options = GroupByOptions()

    fun options(build: GroupByOptions.() -> Unit) {
        options.build()
    }

    fun field(name: String, build: GroupByTransformationFields.() -> Unit) {
        val field = GroupByTransformationFields(name = name).apply(build)
        options.fields += field
    }
}

class FilterFieldsByNameBuilder {
    var id = "filterFieldsByName"
    var options = FilterFieldsByNameOptions()
    fun options(build: FilterFieldsByNameOptions.() -> Unit) {
        options.build()
    }

    fun includeField(name: String) {
        options.includeNames += name
    }
}

class SortByBuilder {
    var id = "sortBy"
    var options = SortByOptions()

    fun options(build: SortByOptions.() -> Unit) {
        options.build()
    }

    fun sortByField(desc: Boolean = true, field: String) {
        options.sort += SortByField(desc = desc, field = field)
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
    var includeByName: Map<String, Boolean>? = emptyMap(),
    var indexByName: Map<String, Int>? = emptyMap(),
    var renameByName: Map<String, String>? = emptyMap()
) : TransformationOptionsBuilder() {
    override fun toJson(): JSONObject = jsonObject {
        "excludeByName" to excludeByName
        "includeByName" to includeByName
        "indexByName" to indexByName
        "renameByName" to renameByName
    }
}

class SortByOptions(
    var sort: List<SortByField> = emptyList(),
) : TransformationOptionsBuilder() {

    override fun toJson(): JSONObject = jsonObject {
        "fields" to jsonObject {}
        "sort" to sort.map { field ->
            field.toJson()
        }
    }
}

class SortByField(
    var desc: Boolean = true,
    var field: String
) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "desc" to desc
        "field" to field
    }
}

class FilterFieldsByNameOptions(
    var includeNames: Array<String> = emptyArray(),
) : TransformationOptionsBuilder() {

    override fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        if (includeNames.isNotEmpty()) {
            val includeObject = JSONObject().put("names", JSONArray(includeNames))
            jsonObject.put("include", includeObject)
        }
        return jsonObject
    }
}

class GroupByOptions(
    var fields: List<GroupByTransformationFields> = emptyList()
) : TransformationOptionsBuilder() {

    override fun toJson(): JSONObject = jsonObject {
        val fieldsJson = JSONObject()
        fields.forEach { field ->
            fieldsJson.put(field.name, field.toJson())
        }
        "fields" to fieldsJson
    }
}

class GroupByTransformationFields(
    var name: String = "",
    var aggregations: Array<String> = emptyArray(),
    var operation: String? = null
) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "aggregations" to aggregations
        "operation" to operation
    }
}
