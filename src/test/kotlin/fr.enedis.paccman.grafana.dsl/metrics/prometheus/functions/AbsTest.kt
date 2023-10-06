package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class AbsTest {
    @Test
    fun `should apply abs function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().abs()

        // then
        metric.asString() shouldBeEqualTo  "abs(metric_name)"
    }
}