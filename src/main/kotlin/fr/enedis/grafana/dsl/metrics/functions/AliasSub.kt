/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * AliasSub graphite function. Runs series names through a regex search/replace.
 *
 * [aliasSub](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.aliasSub)
 */
class AliasSub(
    private val metric: Metric,
    private val search: String,
    private val replace: String
) : Metric {
    override fun asString() = "aliasSub(${metric.asString()}, '${search}', '${replace}')"
}

fun String.aliasSub(search: String, replace: String) = AliasSub(StringMetric(this), search.toString(), replace.toString())

fun Metric.aliasSub(search: String, replace: String) = AliasSub(this, search.toString(), replace.toString())