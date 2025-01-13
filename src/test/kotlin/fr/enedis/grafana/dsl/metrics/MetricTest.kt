/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class MetricTest {

    @Test
    fun `should create empty JSON array`() {
        // given
        val metrics = Metrics(emptyList())

        // then
        metrics.toJson().toString() shouldBeEqualTo "[]"
    }
}