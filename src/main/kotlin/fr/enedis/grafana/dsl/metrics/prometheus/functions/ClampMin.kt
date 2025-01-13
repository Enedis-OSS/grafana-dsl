/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `clamp_min(v instant-vector, min scalar)` clamps the sample values of all elements
 * in `v` to have a lower limit of `min`.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#clamp_min)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class ClampMin internal constructor(
    private val metric: InstantVectorTypedMetric,
    private val min: Double
) : InstantVectorTypedMetric {
    override fun asString() = "clamp_min(${metric.asString()}, $min)"
}

/**
 * Clamp the sample values of all elements in [this] vector to have a lower [limit] of [min].
 */
fun InstantVectorTypedMetric.clampMin(min: Double): InstantVectorTypedMetric = ClampMin(this, min)