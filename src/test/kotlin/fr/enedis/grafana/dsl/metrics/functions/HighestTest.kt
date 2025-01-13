/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.variables.VariablesBuilder
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class HighestTest {

    @Test
    fun `should create highestCurrent metric with int n`() {
        // given
        val metric = "*.*.count" highestCurrent 5

        // then
        metric.asString() shouldBeEqualTo "highestCurrent(*.*.count, 5)"
    }

    @Test
    fun `should create highestCurrent metric with int n and metric`() {
        // given
        val metric = StringMetric("*.*.count") highestCurrent 5

        // then
        metric.asString() shouldBeEqualTo "highestCurrent(*.*.count, 5)"
    }

    @Test
    fun `should create highestCurrent metric with variable n`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = "*.*.count" highestCurrent n

        // then
        metric.asString() shouldBeEqualTo "highestCurrent(*.*.count, \$n)"
    }

    @Test
    fun `should create highestCurrent metric with variable n and metric`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = StringMetric("*.*.count") highestCurrent n

        // then
        metric.asString() shouldBeEqualTo "highestCurrent(*.*.count, \$n)"
    }

    @Test
    fun `should create highestMax metric with int n`() {
        // given
        val metric = "*.*.count" highestMax 5

        // then
        metric.asString() shouldBeEqualTo "highestMax(*.*.count, 5)"
    }

    @Test
    fun `should create highestMax metric with int n and metric`() {
        // given
        val metric = StringMetric("*.*.count") highestMax 5

        // then
        metric.asString() shouldBeEqualTo "highestMax(*.*.count, 5)"
    }

    @Test
    fun `should create highestMax metric with variable n`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = "*.*.count" highestMax n

        // then
        metric.asString() shouldBeEqualTo "highestMax(*.*.count, \$n)"
    }

    @Test
    fun `should create highestMax metric with variable n and metric`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = StringMetric("*.*.count") highestMax n

        // then
        metric.asString() shouldBeEqualTo "highestMax(*.*.count, \$n)"
    }

    @Test
    fun `should create highestAverage metric with int n`() {
        // given
        val metric = "*.*.count" highestAverage 5

        // then
        metric.asString() shouldBeEqualTo "highestAverage(*.*.count, 5)"
    }

    @Test
    fun `should create highestAverage metric with int n and metric`() {
        // given
        val metric = StringMetric("*.*.count") highestAverage 5

        // then
        metric.asString() shouldBeEqualTo "highestAverage(*.*.count, 5)"
    }

    @Test
    fun `should create highestAverage metric with variable n`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = "*.*.count" highestAverage n

        // then
        metric.asString() shouldBeEqualTo "highestAverage(*.*.count, \$n)"
    }

    @Test
    fun `should create highestAverage metric with variable n and metric`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = StringMetric("*.*.count") highestAverage n

        // then
        metric.asString() shouldBeEqualTo "highestAverage(*.*.count, \$n)"
    }
}