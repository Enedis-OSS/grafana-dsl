package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class ExpTest {
    @Test
    fun `should apply exp function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().exp()

        // then
        metric.asString() shouldBeEqualTo  "exp(metric_name)"
    }
}