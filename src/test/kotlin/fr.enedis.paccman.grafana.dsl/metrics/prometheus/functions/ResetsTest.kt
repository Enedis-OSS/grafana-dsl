package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector

class ResetsTest {
    @Test
    fun `should apply resets function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().resets()

        // then
        metric.asString() shouldBeEqualTo  "resets(metric_name[5s])"
    }
}