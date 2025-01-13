/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ConsolidateByTest {

    @Test
    fun `should create metric that consolidate by max function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy MAX

        // then
        metric.asString() shouldBeEqualTo "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'max')"
    }

    @Test
    fun `should create metric that consolidate by min function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy MIN

        // then
        metric.asString() shouldBeEqualTo "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'min')"
    }

    @Test
    fun `should create metric that consolidate by sum function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy SUM

        // then
        metric.asString() shouldBeEqualTo "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'sum')"
    }

    @Test
    fun `should create metric that consolidate by average function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy AVERAGE

        // then
        metric.asString() shouldBeEqualTo "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'average')"
    }

    @Test
    fun `should create metric that consolidate by first function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy FIRST

        // then
        metric.asString() shouldBeEqualTo "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'first')"
    }

    @Test
    fun `should create metric that consolidate by last function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy LAST

        // then
        metric.asString() shouldBeEqualTo "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'last')"
    }
}