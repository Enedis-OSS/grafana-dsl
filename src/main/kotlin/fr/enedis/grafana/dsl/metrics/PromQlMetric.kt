/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.metrics.prometheus.PrometheusMetric

/**
 * Metric for PromQL datasource that integrates for panels.
 *
 * @author lokshin (lokshin@yamoney.ru)
 * @since 08.08.2021
 */
class PromQlMetric (
    val metric: PrometheusMetric,
    val legendFormat: String?,
    val instant: Boolean,
    override val id: String? = null,
    val hidden: Boolean = false
) : DashboardMetric {

    override fun toJson() = jsonObject {
        "refId" to id
        "hide" to hidden
        "expr" to metric.asString()
        "format" to "time_series"
        "legendFormat" to legendFormat
        "instant" to instant
    }

}