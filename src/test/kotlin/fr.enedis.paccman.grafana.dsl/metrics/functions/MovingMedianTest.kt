package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.time.m

class MovingMedianTest {
    @Test
    fun `should create metric that gets median of metric`() {
        // given
        val metric = "*.*.count" movingMedian 30.m

        // then
        metric.asString() shouldEqual "movingMedian(*.*.count, '30m')"
    }
}