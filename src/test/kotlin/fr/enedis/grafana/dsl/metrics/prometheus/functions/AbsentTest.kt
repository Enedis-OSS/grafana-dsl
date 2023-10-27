package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AbsentTest {
    @Test
    fun `should apply absent function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().absent()

        // then
        metric.asString() shouldBeEqualTo  "absent(metric_name)"
    }
}