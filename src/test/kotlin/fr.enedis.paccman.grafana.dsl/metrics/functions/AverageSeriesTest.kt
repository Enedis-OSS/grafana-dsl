package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * [AverageSeries] Test
 */
class AverageSeriesTest {

    @Test
    fun `should create metric that gets averageSeries (string)`() {
        "*.*.mean".averageSeries().asString() shouldEqual "averageSeries(*.*.mean)"
    }

    @Test
    fun `should create metric that gets averageSeries (metric)`() {
        StringMetric("*.*.mean").averageSeries().asString() shouldEqual "averageSeries(*.*.mean)"
    }
}