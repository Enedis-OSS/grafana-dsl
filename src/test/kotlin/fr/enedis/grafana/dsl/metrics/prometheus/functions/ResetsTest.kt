/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ResetsTest {
    @Test
    fun `should apply resets function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().resets()

        // then
        metric.asString() shouldBeEqualTo  "resets(metric_name[5s])"
    }
}