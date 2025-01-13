/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class AliasByMetricTest {

    @Test
    fun `should create metric that gets derivative (string)`() {
        "*.*.mean".aliasByMetric().asString() shouldBeEqualTo "aliasByMetric(*.*.mean)"
    }

    @Test
    fun `should create metric that gets derivative (metric)`() {
        StringMetric("*.*.mean").aliasByMetric().asString() shouldBeEqualTo "aliasByMetric(*.*.mean)"
    }
}