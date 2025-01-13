/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.set
import fr.enedis.grafana.dsl.metrics.DashboardMetric

class AlertQuery(
    private val datasourceId: Long = 1,
    private val metric: DashboardMetric? = null,
    private vararg val params: Any
) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["datasourceId"] = datasourceId
        if (metric != null) {
            json["model"] = metric.toJson()
        }
        json["params"] = JSONArray(params.toList())

        return json
    }
}