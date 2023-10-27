package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class IrateTest {
    @Test
    fun `should apply irate function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().irate()

        // then
        metric.asString() shouldBeEqualTo  "irate(metric_name[5s])"
    }
}