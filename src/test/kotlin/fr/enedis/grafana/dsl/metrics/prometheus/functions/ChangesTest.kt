package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ChangesTest {
    @Test
    fun `should apply changes function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().changes()

        // then
        metric.asString() shouldBeEqualTo  "changes(metric_name[5s])"
    }
}