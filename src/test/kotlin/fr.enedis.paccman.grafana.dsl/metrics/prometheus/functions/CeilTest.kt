package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class CeilTest {
    @Test
    fun `should apply ceil function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().ceil()

        // then
        metric.asString() shouldBeEqualTo  "ceil(metric_name)"
    }
}