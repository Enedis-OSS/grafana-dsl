/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import fr.enedis.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * For each input time series, `changes(v range-vector)` returns the number of times its value has changed within
 * the provided time range as an instant vector
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#changes)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Changes internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "changes(${metric.asString()})"
}

/**
 * Return the number of times [this] vector's value has changed within the provided time range as an instant vector
 */
fun RangeVectorTypedMetric.changes(): InstantVectorTypedMetric = Changes(this)