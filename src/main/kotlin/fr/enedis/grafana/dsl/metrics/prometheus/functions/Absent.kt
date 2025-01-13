/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `absent(v instant-vector)` returns an empty vector if the vector passed to it has any elements
 * and a 1-element vector with the value 1 if the vector passed to it has no elements.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#absent)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Absent internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "absent(${metric.asString()})"
}

/**
 * Convert to an empty vector if [this] vector has any elements
 * and a 1-element vector with the value 1 if [this] vector has no elements
 */
fun InstantVectorTypedMetric.absent(): InstantVectorTypedMetric = Absent(this)