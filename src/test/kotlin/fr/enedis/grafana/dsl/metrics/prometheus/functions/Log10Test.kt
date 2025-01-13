/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Log10Test {
    @Test
    fun `should apply log10 function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().log10()

        // then
        metric.asString() shouldBeEqualTo  "log10(metric_name)"
    }
}