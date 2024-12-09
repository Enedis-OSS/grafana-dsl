package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class ExcludeTest {

    @Test
    fun `should create metric with exclusion`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" exclude "ping"

        // then
        metric.asString() shouldEqual "exclude(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 'ping')"
    }
}
