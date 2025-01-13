/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TransformNullTest {
    @Test
    fun `should create metric that transform metric and replace nulls to constant`() {
        // given
        val metric = "*.*.count" transformNull 0.0

        // then
        metric.asString() shouldBeEqualTo "transformNull(*.*.count, 0.0)"
    }
}