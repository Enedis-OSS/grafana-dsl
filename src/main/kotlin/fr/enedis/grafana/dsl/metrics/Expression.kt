/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import fr.enedis.grafana.dsl.DashboardElement
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

class Expression constructor(
    override val id: String,
    private val type: String,
    val expression: String,
    val hide: Boolean = false,
    val reducer: String,
    val mode: String,
) : DashboardMetric {
    override fun toJson(): JSONObject {
        return jsonObject {
            "refId" to id
            "expression" to expression
            "type" to type
            "hide" to hide
            "reducer" to reducer
            "settings" to jsonObject { "mode" to mode }
            "datasource" to jsonObject {
                "type" to "__expr__"
                "uid" to "__expr__"
            }
        }
    }

    @DashboardElement
    class Builder {
        class Metric {
            var id: String? = null
            var type: String = "math"
            var expression: String = ""
            var hide: Boolean = false
            var reducer: String = "last"
            var mode: String = "dropNN"

            internal fun createMetric() = Expression(
                id = id ?: "A",
                type = type,
                expression = expression,
                hide = hide,
                reducer = reducer,
                mode = mode,
            )
        }
    }
}

/**
 * Generates metric id
 *
 */
internal class ExpressionQueryIdGenerator {
    private var lastId: Int? = null

    /**
     * Return next metric id
     *
     * @throws IllegalStateException if last generated id was 'Z'
     */
    fun nextMetricId(): String {
        val nextId = lastId?.plus(1) ?: 1
        lastId = nextId
        return nextId.toString()
    }
}