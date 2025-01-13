/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Value mapping interface
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
interface Mapping : Json<JSONObject> {
    val type: String
}

/**
 * Mapping: value -> text
 * @author Aleksey Matveev
 * @since 03.11.2020
 * */
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
 * Mapping: from..to -> text
 * @author Aleksey Matveev
 * @since 03.11.2020
 * */
class RangeToTextMapping(
    private val from: String = "",
    private val to: String = "",
    private val text: String = ""
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

/**
 * Mapping: value -> color
 * @author Michaël Valentino
 * @since 09.12.2024
 * */
class RangeToColorMapping(
    private val from: String = "",
    private val to: String = "",
    private val color: String = ""
) : Mapping {
    override val type: String = "range"

    override fun toJson() = jsonObject {
        "type" to type
        "options" to jsonObject {
            "from" to from
            "to" to to
            "result" to jsonObject {
                "color" to color
            }
        }
    }

    class Builder {
        var from: String = ""
        var to: String = ""
        var color: String = ""

        val rangeToColor = mutableListOf<RangeToColorMapping>()

        fun range(from: String, to: String, color: String) {
            rangeToColor += RangeToColorMapping(from = from, to = to, color = color)
        }
    }
}