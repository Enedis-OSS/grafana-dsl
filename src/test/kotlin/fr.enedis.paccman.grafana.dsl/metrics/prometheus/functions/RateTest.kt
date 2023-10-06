package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector

class RateTest {
    @Test
    fun `should apply rate function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().rate()

        // then
        metric.asString() shouldBeEqualTo  "rate(metric_name[5s])"
    }
}