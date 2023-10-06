package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class GroupTest {
    @Test
    fun `should create metric that group metrics`() {
        // given
        val metric = "*.request.count".group(StringMetric("*.response.upper"))

        // then
        metric.asString() shouldEqual "group(*.request.count,*.response.upper)"
    }
}