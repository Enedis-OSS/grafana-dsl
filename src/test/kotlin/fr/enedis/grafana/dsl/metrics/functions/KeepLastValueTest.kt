package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KeepLastValueTest {

    @Test
    fun `should create metric with keepLastValue function by raw string metric`() {
        // given
        val metric = "*.first.count" keepLastValue 10

        // then
        metric.asString() shouldBeEqualTo "keepLastValue(*.first.count, 10)"
    }

    @Test
    fun `should create metric with keepLastValue function by metric object`() {
        // given
        val metric = StringMetric("*.first.count") keepLastValue 10

        // then
        metric.asString() shouldBeEqualTo "keepLastValue(*.first.count, 10)"
    }
}