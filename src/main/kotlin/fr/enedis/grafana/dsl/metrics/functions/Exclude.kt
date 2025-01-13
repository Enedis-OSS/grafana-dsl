/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

class Exclude(private val metric: Metric, private val exclusion: String) : Metric {
    override fun asString() = "exclude(${metric.asString()}, '$exclusion')"
}

infix fun String.exclude(exclusion: String) = Exclude(StringMetric(this), exclusion)

infix fun Metric.exclude(exclusion: String) = Exclude(this, exclusion)