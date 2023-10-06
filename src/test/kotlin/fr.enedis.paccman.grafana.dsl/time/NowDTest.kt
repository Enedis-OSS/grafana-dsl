package fr.enedis.grafana.dsl.time

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * [nowD] Test
 */
class NowDTest {

    @Test
    fun `should stringify correctly`() {
        // expect
        nowD.toJson() shouldEqual "now/d"
        nowD.toString() shouldEqual "now/d"
    }
}
