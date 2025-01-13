/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * Generator for sumSeries function for graphite. sumSeries combines all metrics and returns sum at each point.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class SumSeries(private val metric: Metric) : Metric {
    override fun asString() = """sumSeries(${metric.asString()})"""
}

fun String.sumSeries() = SumSeries(StringMetric(this))

fun Metric.sumSeries() = SumSeries(this)