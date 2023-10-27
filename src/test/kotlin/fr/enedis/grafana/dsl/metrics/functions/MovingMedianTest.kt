package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.time.m
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class MovingMedianTest {
    @Test
    fun `should create metric that gets median of metric`() {
        // given
        val metric = "*.*.count" movingMedian 30.m

        // then
        metric.asString() shouldBeEqualTo "movingMedian(*.*.count, '30m')"
    }
}