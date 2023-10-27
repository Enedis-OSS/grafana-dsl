package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AbsentOverTimeTest {
    @Test
    fun `should apply absent_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().absentOverTime()

        // then
        metric.asString() shouldBeEqualTo  "absent_over_time(metric_name[5s])"
    }
}