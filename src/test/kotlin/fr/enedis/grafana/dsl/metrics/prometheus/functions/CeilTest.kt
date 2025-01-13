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

class CeilTest {
    @Test
    fun `should apply ceil function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().ceil()

        // then
        metric.asString() shouldBeEqualTo  "ceil(metric_name)"
    }
}