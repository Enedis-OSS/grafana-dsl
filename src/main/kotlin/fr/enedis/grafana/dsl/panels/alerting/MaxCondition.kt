/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.set

/**
 * Generator for max alert condition.
 * @author abramovgerman
 * @since 02.12.2020
 */
class MaxCondition(private val condition: AlertingCondition) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = condition.toJson()

        json["reducer"] = AlertEvaluator("max").toJson()

        val operator = JSONObject()
        operator["type"] = "and"

        json["operator"] = operator

        return json
    }
}