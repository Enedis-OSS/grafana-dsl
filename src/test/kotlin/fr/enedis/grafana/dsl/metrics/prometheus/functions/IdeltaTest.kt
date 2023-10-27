package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class IdeltaTest {
    @Test
    fun `should apply idelta function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().idelta()

        // then
        metric.asString() shouldBeEqualTo  "idelta(metric_name[5s])"
    }
}