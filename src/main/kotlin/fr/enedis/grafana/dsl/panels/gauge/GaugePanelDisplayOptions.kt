/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.gauge

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Options to refine visualization
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class GaugePanelDisplayOptions(
    private val reduceOptions: GaugePanelReduceOptions = GaugePanelReduceOptions(),
    private val showThresholdLabels: Boolean = false,
    private val showThresholdMarkers: Boolean = true,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "showThresholdLabels" to showThresholdLabels
        "showThresholdMarkers" to showThresholdMarkers
    }
}

/**
 * Builder for GaugePanelDisplayOptions
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class GaugePanelDisplayOptionsBuilder {
    private var reduceOptions: GaugePanelReduceOptions = GaugePanelReduceOptions()
    var showThresholdLabels: Boolean = false
    var showThresholdMarkers: Boolean = false
    fun reduceOptions(build: GaugePanelReduceOptionsBuilder.() -> Unit) {
        val builder = GaugePanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun createGaugePanelDisplayOptions() = GaugePanelDisplayOptions(reduceOptions = reduceOptions)
}

/**
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class GaugePanelReduceOptions(
    private val values: Boolean = false,
    private val calcs: List<String> = listOf("lastNotNull"),
    private val fields: String = ""
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to values
        "calcs" to calcs
        "fields" to fields
    }
}

class GaugePanelReduceOptionsBuilder {
    var fields: String = ""
    var values: Boolean = false
    var calcs: List<String> = listOf("lastNotNull")
    fun createReduceOptions(): GaugePanelReduceOptions {
        return GaugePanelReduceOptions(fields = fields, values = values, calcs = calcs)
    }
}