package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector

class DeltaTest {
    @Test
    fun `should apply delta function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().delta()

        // then
        metric.asString() shouldBeEqualTo  "delta(metric_name[5s])"
    }
}