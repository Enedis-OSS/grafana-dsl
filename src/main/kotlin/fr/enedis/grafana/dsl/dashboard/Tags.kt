/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json

/**
 * Tags, used to search for dashboard in Grafana.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Tags(private val values: List<String>) : Json<JSONArray> {

    override fun toJson() = JSONArray(values)
}