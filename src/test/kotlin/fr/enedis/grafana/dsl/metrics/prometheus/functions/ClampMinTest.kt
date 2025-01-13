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

class ClampMinTest {
    @Test
    fun `should apply clamp_min function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().clampMin(10.0)

        // then
        metric.asString() shouldBeEqualTo  "clamp_min(metric_name, 10.0)"
    }
}