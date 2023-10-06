package fr.enedis.grafana.dsl.time

import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.shouldEqualToJson

class TimeRangeTest {

    @Test
    fun `should create range correctly`() {
        // when
        val range = now - 1.w..now

        // then
        range.toJson().toString() shouldEqualToJson """{"from": "now-1w", "to": "now"}"""
    }
}