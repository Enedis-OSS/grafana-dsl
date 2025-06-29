/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * Generator for removeAbovePercentile function for graphite.
 * Removes data above the nth percentile from the series or list of series provided.
 * Values above this percentile are assigned a value of None.
 * @author abramovgerman
 * @since 02.12.2020
 */
class RemoveAbovePercentile(private val metric: Metric, private val n: Int) : Metric {
    override fun asString() = """removeAbovePercentile(${metric.asString()}, $n)"""
}

fun String.removeAbovePercentile(n: Int) = RemoveAbovePercentile(StringMetric(this), n)

fun Metric.removeAbovePercentile(n: Int) = RemoveAbovePercentile(this, n)