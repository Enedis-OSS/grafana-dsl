/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.set

class AlertingPanelDecorator(
    private val alert: Alert,
    private val thresholds: Thresholds
) : (JSONObject) -> Unit {

    override fun invoke(json: JSONObject) {
        json["alert"] = alert.toJson()
        json["thresholds"] = thresholds.toJson()
    }
}