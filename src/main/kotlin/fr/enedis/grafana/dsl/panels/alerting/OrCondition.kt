/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.set

class OrCondition(private val condition: AlertingCondition) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = condition.toJson()

        val operator = JSONObject()
        operator["type"] = "or"

        json["operator"] = operator

        return json
    }
}