/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AliasByNodeTest {

    @Test
    fun `should create metric that aliased by node`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 1

        // then
        metric.asString() shouldBeEqualTo "aliasByNode(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 1)"
    }

    @Test
    fun `should create metric that aliased by nodes`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".aliasByNode(1, 2)

        // then
        metric.asString() shouldBeEqualTo "aliasByNode(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 1, 2)"
    }
}