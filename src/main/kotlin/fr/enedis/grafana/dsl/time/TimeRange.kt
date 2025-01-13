/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.time

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Time range.
 *
 * @property from Start
 * @property to End
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class TimeRange(private val from: Timestamp, private val to: Timestamp) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "from" to from
        "to" to to
    }
}