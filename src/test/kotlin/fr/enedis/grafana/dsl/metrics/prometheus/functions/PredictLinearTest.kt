package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class PredictLinearTest {
    @Test
    fun `should apply abs function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().predictLinear(15)

        // then
        metric.asString() shouldBeEqualTo  "predict_linear(metric_name[5s], 15)"
    }
}