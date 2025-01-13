/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `exp(v instant-vector)` calculates the exponential function for all elements in `v`.
 *
 * Special cases are:
 *  * Exp(+Inf) = +Inf
 *  * Exp(NaN) = NaN
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#exp)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Exp internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "exp(${metric.asString()})"
}

/**
 * Calculate the exponential function for all elements in [this] vector
 */
fun InstantVectorTypedMetric.exp(): InstantVectorTypedMetric = Exp(this)