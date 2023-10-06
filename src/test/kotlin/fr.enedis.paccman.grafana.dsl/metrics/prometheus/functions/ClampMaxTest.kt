package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class ClampMaxTest {
    @Test
    fun `should apply clamp_max function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().clampMax(20.0)

        // then
        metric.asString() shouldBeEqualTo  "clamp_max(metric_name, 20.0)"
    }
}