package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Log2Test {
    @Test
    fun `should apply log2 function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().log2()

        // then
        metric.asString() shouldBeEqualTo  "log2(metric_name)"
    }
}