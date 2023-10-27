package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GroupTest {
    @Test
    fun `should create metric that group metrics`() {
        // given
        val metric = "*.request.count".group(StringMetric("*.response.upper"))

        // then
        metric.asString() shouldBeEqualTo "group(*.request.count,*.response.upper)"
    }
}