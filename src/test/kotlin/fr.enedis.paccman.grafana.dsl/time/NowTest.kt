package fr.enedis.grafana.dsl.time

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class NowTest {

    @Test
    fun `should stringify correctly`() {
        // expect
        now.toJson() shouldEqual "now"
        now.toString() shouldEqual "now"
    }

    @Test
    fun `should create new timestamp with minus used`() {
        // when
        val timestamp = now - 1.w

        // then
        timestamp.toJson() shouldEqual "now-1w"
        timestamp.toString() shouldEqual "now-1w"
    }
}
