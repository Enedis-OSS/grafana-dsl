/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

class GroupByNode internal constructor(
    private val metric: Metric,
    private val node: Int,
    private val function: String = "sum"
) : Metric {
    override fun asString() = "groupByNode(${metric.asString()}, $node, '$function')"
}

fun String.groupByNode(node: Int, function: String) = GroupByNode(StringMetric(this), node, function)

infix fun String.groupByNode(node: Int) = GroupByNode(StringMetric(this), node)

fun Metric.groupByNode(node: Int, function: String) = GroupByNode(this, node, function)

infix fun Metric.groupByNode(node: Int) = GroupByNode(this, node)