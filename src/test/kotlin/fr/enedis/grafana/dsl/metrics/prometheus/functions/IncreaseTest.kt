package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class IncreaseTest {
    @Test
    fun `should apply increase function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().increase()

        // then
        metric.asString() shouldBeEqualTo  "increase(metric_name[5s])"
    }
}