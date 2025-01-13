/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray

/**
 * Metrics collection.
 *
 * @author Dmitry Komarov
 * @since 7/23/18
 */
class Metrics(dashboardMetrics: Collection<DashboardMetric>) : Json<JSONArray> by JsonArray(dashboardMetrics)