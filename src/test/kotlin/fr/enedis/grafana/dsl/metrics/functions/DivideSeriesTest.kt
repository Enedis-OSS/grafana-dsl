package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class DivideSeriesTest {
    @Test
    fun `should create metric that divides two series`() {
        // given
        val metric = "*.first.count" divideSeries "*.second.count"

        // then
        metric.asString() shouldBeEqualTo "divideSeries(*.first.count, *.second.count)"
    }
}