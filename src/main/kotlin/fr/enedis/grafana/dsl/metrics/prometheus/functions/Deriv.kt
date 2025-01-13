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
 * Function `deriv(v range-vector)` calculates the per-second derivative of the time series in a range vector `v`,
 * [using simple linear regression](https://en.wikipedia.org/wiki/Simple_linear_regression).
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#deriv)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Deriv internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "deriv(${metric.asString()})"
}

/**
 * Calculate the per-second derivative of the time series in [this] vector, using simple linear regression
 */
fun RangeVectorTypedMetric.deriv(): InstantVectorTypedMetric = Deriv(this)