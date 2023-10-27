package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class FloorTest {
    @Test
    fun `should apply floor function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().floor()

        // then
        metric.asString() shouldBeEqualTo  "floor(metric_name)"
    }
}