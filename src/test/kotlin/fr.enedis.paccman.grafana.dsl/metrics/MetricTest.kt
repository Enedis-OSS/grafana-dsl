package fr.enedis.grafana.dsl.metrics

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class MetricTest {

    @Test
    fun `should create empty JSON array`() {
        // given
        val metrics = Metrics(emptyList())

        // then
        metrics.toJson().toString() shouldEqual "[]"
    }
}