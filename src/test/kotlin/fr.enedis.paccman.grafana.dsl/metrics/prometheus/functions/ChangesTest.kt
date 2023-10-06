package fr.enedis.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector

class ChangesTest {
    @Test
    fun `should apply changes function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().changes()

        // then
        metric.asString() shouldBeEqualTo  "changes(metric_name[5s])"
    }
}