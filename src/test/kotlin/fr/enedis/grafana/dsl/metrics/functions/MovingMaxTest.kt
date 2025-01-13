/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.time.m
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Aleksey Matveev
 * @since 23.11.2020
 */
class MovingMaxTest {
    @Test
    fun `should create metric that gets max of metric`() {
        // given
        val metric = "*.*.count" movingMax 30.m

        // then
        metric.asString() shouldBeEqualTo "movingMax(*.*.count, '30m')"
    }

    @Test
    fun `should create metric that gets max of metric by metric object`() {
        // given
        val metric = StringMetric("*.*.count") movingMax 30.m

        // then
        metric.asString() shouldBeEqualTo "movingMax(*.*.count, '30m')"
    }
}