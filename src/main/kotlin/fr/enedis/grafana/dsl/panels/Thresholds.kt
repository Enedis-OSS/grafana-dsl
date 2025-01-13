/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Thresholds set the color of either the value text or the background depending on conditions that you define
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
class Thresholds(
    private val mode: ThresholdMode = ThresholdMode.ABSOLUTE,
    private val steps: List<ThresholdsStep> = emptyList()
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "mode" to mode.value
        "steps" to JsonArray(steps)
    }
}

class ThresholdsBuilder(private val mode: ThresholdMode = ThresholdMode.ABSOLUTE) {
    private var steps: List<ThresholdsStep> = emptyList()

    fun steps(build: ThresholdsStepBuilder.() -> Unit) {
        val builder = ThresholdsStepBuilder()
        builder.build()
        steps = builder.steps
    }

    fun createThresholds() = Thresholds(mode, steps)
}

class ThresholdsStep(private val value: String = "null", private val color: Color = Color.GREEN) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "value" to (value.toDoubleOrNull() ?: "null")
        "color" to color.asHexString()
    }
}

class ThresholdsStepBuilder {
    val steps = mutableListOf<ThresholdsStep>()

    infix fun String.to(color: Color) {
        steps += ThresholdsStep(this, color)
    }
}