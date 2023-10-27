package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * [AverageSeries] Test
 */
class AverageSeriesTest {

    @Test
    fun `should create metric that gets averageSeries (string)`() {
        "*.*.mean".averageSeries().asString() shouldBeEqualTo "averageSeries(*.*.mean)"
    }

    @Test
    fun `should create metric that gets averageSeries (metric)`() {
        StringMetric("*.*.mean").averageSeries().asString() shouldBeEqualTo "averageSeries(*.*.mean)"
    }
}