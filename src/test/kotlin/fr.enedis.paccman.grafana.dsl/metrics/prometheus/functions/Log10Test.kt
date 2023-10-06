package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector

class Log10Test {
    @Test
    fun `should apply log10 function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().log10()

        // then
        metric.asString() shouldBeEqualTo  "log10(metric_name)"
    }
}