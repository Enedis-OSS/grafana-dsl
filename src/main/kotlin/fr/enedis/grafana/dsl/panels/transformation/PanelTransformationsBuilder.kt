/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.transformation

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
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
        transformations += builder.createPanelTransformation()
    }

    fun organizeField(build: OrganizeFieldsBuilder.() -> Unit) {
        val builder = OrganizeFieldsBuilder()
        builder.build()
        transformations += builder.createPanelTransformation()
    }

    fun sortBy(build: SortByBuilder.() -> Unit) {
        val builder = SortByBuilder()
        builder.build()
        transformations += builder.createPanelTransformation()
    }

    fun filterFieldsByName(build: FilterFieldsByNameBuilder.() -> Unit) {
        val builder = FilterFieldsByNameBuilder()
        builder.build()
        transformations += builder.createPanelTransformation()
    }

    fun filterFieldsByValue(build: FilterFieldsByValueBuilder.() -> Unit) {
        val builder = FilterFieldsByValueBuilder()
        builder.build()
        transformations += builder.createPanelTransformation()
    }

    fun groupBy(build: GroupByBuilder.() -> Unit) {
        val builder = GroupByBuilder()
        builder.build()
        transformations += builder.createPanelTransformation()
    }

    fun reduce(build: PanelTransformationReduceBuilder.() -> Unit) {
        val builder = PanelTransformationReduceBuilder()
        builder.build()
        transformations += builder.createPanelTransformation()
    }
}

class CalculateFieldBuilder {
    var id = "calculateField"
    var filter: PanelTransformationFilter? = null
    var options = CalculateFieldOptions()

    fun filter(build: PanelTransformationFilterBuilder.() -> Unit) {
        val builder = PanelTransformationFilterBuilder()
        builder.build()
        filter = builder.createPanelTransformationFilter()
    }

    fun byRefId(options: String) {
        filter = PanelTransformationFilter(id="byRefId", options = options)
    }

    fun options(build: CalculateFieldOptions.() -> Unit) {
        val builder = CalculateFieldOptions()
        builder.build()
        options = builder
    }

    fun createPanelTransformation() = PanelTransformation(id=id, options=options, filter=filter)
}

class OrganizeFieldsBuilder {
    var id = "organize"
    var filter: PanelTransformationFilter? = null
    var options = OrganizeFieldsOptions()

    fun options(build: OrganizeFieldsOptions.() -> Unit) {
        val builder = OrganizeFieldsOptions()
        builder.build()
        options = builder
    }

    fun createPanelTransformation() = PanelTransformation(id=id, options=options, filter=filter)
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

    fun createPanelTransformation() = PanelTransformation(id=id, options=options)
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

    fun createPanelTransformation() = PanelTransformation(id=id, options=options)
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

    fun createPanelTransformation() = PanelTransformation(id=id, options=options)
}

class PanelTransformationReduceBuilder {
    var id = "reduce"
    var options = PanelTransformationReduceOptions()

    fun options(build: PanelTransformationReduceOptionsBuilder.() -> Unit) {
        val builder = PanelTransformationReduceOptionsBuilder()
        builder.build()
        options = builder.createPanelTransformationReduceOptions()
    }

    fun seriesToRows(reducers: List<String>, includeTimeField: Boolean? = null) {
        options = PanelTransformationReduceOptions(mode="seriesToRows", reducers = reducers.toTypedArray(), includeTimeField = includeTimeField)
    }

    fun createPanelTransformation() = PanelTransformation(id=id, options=options)
}

class PanelTransformationReduceOptions(
    var mode: String? = null,
    var reducers: Array<String> = emptyArray(),
    var includeTimeField: Boolean? = null,
) : TransformationOptionsBuilder() {
    override fun toJson(): JSONObject = jsonObject{
        "mode" to mode
        "reducers" to reducers
        "includeTimeField" to includeTimeField
    }
}

class PanelTransformationReduceOptionsBuilder(
    var mode: String? = null,
    var reducers: List<String> = listOf(),
    var includeTimeField: Boolean? = null,
) {
    fun createPanelTransformationReduceOptions(): PanelTransformationReduceOptions =
        PanelTransformationReduceOptions(mode = mode, reducers = reducers.toTypedArray(), includeTimeField = includeTimeField)
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

class FilterFieldsByValueBuilder {
    var id = "filterByValue"
    var options = FilterFieldsByValueOptions()

    fun options(build: FilterFieldsByValueOptions.() -> Unit) {
        options.build()
    }


    fun createPanelTransformation() = PanelTransformation(id = id, options = options)
}

class FilterFieldsByValueOptions(
    var filters: List<FilterFieldsByValueFilter> = emptyList(),
    var match: FilterFieldsByValueMatchType = FilterFieldsByValueMatchType.ANY,
    var type: FilterFieldsByValueFilterType = FilterFieldsByValueFilterType.INCLUDE
) : TransformationOptionsBuilder() {

    override fun toJson(): JSONObject {
        return jsonObject {
            "filters" to JsonArray(filters)
            "type" to type.name.toLowerCase()
            "match" to match.name.toLowerCase()
        }
    }
}

class FilterFieldsByValueFilter(
    var fieldName: String,
    var id: String,
    var value: String
) : Json<JSONObject> {

    override fun toJson(): JSONObject =
        jsonObject {
            "config" to jsonObject {
                "id" to id
                "options" to jsonObject {
                    "value" to value
                }
            }
            "fieldName" to fieldName
        }
}

enum class FilterFieldsByValueFilterType {
    INCLUDE, EXCLUDE
}

enum class FilterFieldsByValueMatchType {
    ANY, ALL
}