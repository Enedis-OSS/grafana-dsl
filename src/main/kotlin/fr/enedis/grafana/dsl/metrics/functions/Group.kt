/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * Takes an arbitrary number of seriesLists and adds them to a single seriesList.
 * This is used to pass multiple seriesLists to a function which only takes one*
 * [group](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.group)
 *
 * @author Aleksandr Korkin
 * @since 12.12.2019
 */
class Group internal constructor(private vararg var seriesList: Metric) : Metric {
    override fun asString() = "group(${seriesList.joinToString(",") { it.asString() }})"
}

fun String.group(vararg metrics: Metric) = Group(StringMetric(this), *metrics)

fun Metric.group(vararg metrics: Metric) = Group(this, *metrics)

fun Metric.group(vararg metrics: String) = Group(this, *metrics.map { i -> StringMetric(i) }.toTypedArray())