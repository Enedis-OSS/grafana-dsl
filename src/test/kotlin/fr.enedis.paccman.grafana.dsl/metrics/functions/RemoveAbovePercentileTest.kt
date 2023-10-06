package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * Test for [fr.enedis.grafana.dsl.metrics.functions.RemoveAbovePercentile]
 * @author abramovgerman
 * @since 02.12.2020
 */
class RemoveAbovePercentileTest {
    @Test
    fun `should create metric with removeAbovePercentile() function`() {
        // given
        val metric = "*.*.count".removeAbovePercentile(90)

        // then
        metric.asString() shouldEqual "removeAbovePercentile(*.*.count, 90)"
    }
}