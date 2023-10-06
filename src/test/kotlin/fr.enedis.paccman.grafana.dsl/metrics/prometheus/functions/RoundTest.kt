package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class RoundTest {
    @Test
    fun `should apply round function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().round(15.4)

        // then
        metric.asString() shouldBeEqualTo  "round(metric_name, 15.4)"
    }
}