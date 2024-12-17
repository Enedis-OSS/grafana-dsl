package fr.enedis.grafana.dsl.time

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DurationTest {

    @ParameterizedTest
    @MethodSource("durations")
    fun `should stringify correctly`(duration: Duration, expected: String) {
        // expect
        duration.asRefreshPeriod() shouldBeEqualTo expected
        duration.toString() shouldBeEqualTo expected
    }

    companion object {
        @JvmStatic
        fun durations() = arrayOf(
            arrayOf(10.s, "10s"),
            arrayOf(10.m, "10m"),
            arrayOf(10.h, "10h"),
            arrayOf(10.d, "10d"),
            arrayOf(10.w, "10w"),
            arrayOf(Duration.auto, "auto")
        )
    }
}
