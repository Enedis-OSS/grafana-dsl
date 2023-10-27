package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ClampTest {
    @Test
    fun `should apply clamp function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().clamp(10.0, 20.0)

        // then
        metric.asString() shouldBeEqualTo  "clamp(metric_name, 10.0, 20.0)"
    }
}