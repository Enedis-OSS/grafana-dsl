/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard.link

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray

/**
 * Links of a dashboard
 */
class DashboardLinks(links: List<DashboardLink>) : Json<JSONArray> by JsonArray(links)