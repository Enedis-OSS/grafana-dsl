package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class SumSeriesTest {
    @Test
    fun `should create metric that sum metrics at each datapoint`() {
        // given
        val metric = "*.*.count".sumSeries()

        // then
        metric.asString() shouldBeEqualTo "sumSeries(*.*.count)"
    }
}