package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class LnTest {
    @Test
    fun `should apply ln function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().ln()

        // then
        metric.asString() shouldBeEqualTo  "ln(metric_name)"
    }
}