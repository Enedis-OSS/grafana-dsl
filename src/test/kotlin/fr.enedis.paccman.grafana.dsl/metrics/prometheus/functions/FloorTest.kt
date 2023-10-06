package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class FloorTest {
    @Test
    fun `should apply floor function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().floor()

        // then
        metric.asString() shouldBeEqualTo  "floor(metric_name)"
    }
}