/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.time.Duration

/**
 * Time range tab in panel.
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
class TimeRange(
    private val lastTime: String? = null,
    private val timeShift: String? = null,
    private val hideTimeOverrideInfo: Boolean = false
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "timeFrom" to lastTime
        "timeShift" to timeShift
        "hideTimeOverride" to hideTimeOverrideInfo
    }
}