/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.time.m
import fr.enedis.grafana.dsl.variables.Variable
import org.amshove.kluent.shouldBeEqualTo
import org.json.JSONObject
import org.junit.jupiter.api.Test

class SummarizeTest {

    @Test
    fun `should create metric from duration and function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".summarize(1.m, "avg")

        // then
        metric.asString() shouldBeEqualTo "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '1m', 'avg')"
    }

    @Test
    fun `should create metric from duration and default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" summarize 1.m

        // then
        metric.asString() shouldBeEqualTo "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '1m', 'sum')"
    }

    @Test
    fun `should create metric from variable and function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".summarize(FakeVariable, "avg")

        // then
        metric.asString() shouldBeEqualTo "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '$${FakeVariable.name}', 'avg')"
    }

    @Test
    fun `should create metric from variable and default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" summarize FakeVariable

        // then
        metric.asString() shouldBeEqualTo "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '$${FakeVariable.name}', 'sum')"
    }

    @Test
    fun `should create metric from variable and function with alignTo`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".summarize(FakeVariable, "avg", true)

        // then
        metric.asString() shouldBeEqualTo "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '$${FakeVariable.name}', 'avg', true)"
    }

    private object FakeVariable : Variable {

        override fun toJson(): JSONObject {
            throw UnsupportedOperationException()
        }

        override val name = "fake"
    }
}