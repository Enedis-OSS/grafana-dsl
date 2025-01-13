/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * Generator for divideSeries function for graphite. divideSeries accepts
 * two metrics and returns result of dividing them to each other.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class DivideSeries(private val firstMetric: Metric, private val secondMetric: Metric) : Metric {
    override fun asString() = """divideSeries(${firstMetric.asString()}, ${secondMetric.asString()})"""
}

infix fun Metric.divideSeries(anotherMetric: Metric) = DivideSeries(this, anotherMetric)

infix fun String.divideSeries(anotherMetric: String) = DivideSeries(StringMetric(this), StringMetric(anotherMetric))