/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * Generator for alias function for graphite. Alias sets the name for the metric that appears below the graph.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 16.11.2018
 */
class Alias(
    private val metric: Metric,
    internal val aliasName: String
) : Metric {
    override fun asString() = "alias(${metric.asString()}, '$aliasName')"
}

infix fun Metric.alias(aliasName: String) = Alias(this, aliasName)

infix fun String.alias(aliasName: String) = Alias(StringMetric(this), aliasName)