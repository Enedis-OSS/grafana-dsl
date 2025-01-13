/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ScaleTest {
    @Test
    fun `should create metric that scales metric to constant`() {
        // given
        val metric = "*.*.count" scale 5.0

        // then
        metric.asString() shouldBeEqualTo "scale(*.*.count, 5.0)"
    }
}