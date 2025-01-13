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
class NonNegativeDerivativeTest {

    @Test
    fun `should create metric that gets nonNegativeDerivative (string)`() {
        "*.*.mean".nonNegativeDerivative().asString() shouldBeEqualTo "nonNegativeDerivative(*.*.mean)"
    }

    @Test
    fun `should create metric that gets nonNegativeDerivative (metric)`() {
        StringMetric("*.*.mean").nonNegativeDerivative().asString() shouldBeEqualTo "nonNegativeDerivative(*.*.mean)"
    }
}