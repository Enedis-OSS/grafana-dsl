/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.timeSeries

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

interface Mapping : Json<JSONObject> {
    val type: String
}

class ValueToTextMapping(
    private val value: String = "",
    private val text: String = ""
) : Mapping {
    override val type: String = "value"

    override fun toJson() = jsonObject {
        "type" to type
        "options" to jsonObject {
            value to jsonObject {
                "text" to text
            }
        }
    }

    class Builder {
        var value: String = ""
        var text: String = ""

        val valueToTexts = mutableListOf<ValueToTextMapping>()

        infix fun String.to(text: String) {
            valueToTexts += ValueToTextMapping(this, text)
        }
    }
}
/**
 * Mapping: value -> jsonObject
 * @author TEYEB Wissem
 * @since 04.03.2025
 * */
class ValueToJsonMapping(
    private val value: String = "",
    private val fieldValueMapping: Map<String, Any>? = mapOf()

) : Mapping {
    override val type: String = "value"

    override fun toJson() = jsonObject {
        "type" to type
        "options" to jsonObject {
            value to jsonObject {
                fieldValueMapping?.entries?.forEach { (key, value) ->
                    key to value
                }
            }
        }
    }

    class Builder {
        var value: String = ""
        var fieldValueMapping: Map<String, Any> = mapOf()
        val valueToJson = mutableListOf<ValueToJsonMapping>()

        infix fun String.toFiled(fieldValueMapping: Map<String, Any>) {
            valueToJson += ValueToJsonMapping(this, fieldValueMapping = fieldValueMapping)
        }
    }
}

class RangeToTextMapping(
    private val from: String = "",
    private val to: String = "",
    private val text: String = "",
) : Mapping {
    override val type: String = "range"

    override fun toJson() = jsonObject {
        "type" to type
        "options" to jsonObject {
            "from" to from
            "to" to to
            "result" to jsonObject {
                "text" to text
            }
        }
    }

    class Builder {
        var from: String = ""
        var to: String = ""
        var text: String = ""

        val rangeToTexts = mutableListOf<RangeToTextMapping>()

        fun range(from: String, to: String, text: String) {
            rangeToTexts += RangeToTextMapping(from = from, to = to, text = text)
        }
    }
}
