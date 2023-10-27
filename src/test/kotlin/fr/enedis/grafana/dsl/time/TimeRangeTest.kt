package fr.enedis.grafana.dsl.time

import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class TimeRangeTest {

    @Test
    fun `should create range correctly`() {
        // when
        val range = now - 1.w..now

        // then
        range.toJson().toString() shouldEqualToJson """{"from": "now-1w", "to": "now"}"""
    }
}