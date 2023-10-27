package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asRangeVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class HoltWintersTest {

    @ParameterizedTest
    @MethodSource("functionValidArguments")
    fun `should apply holtWinters function to prometheusMetric`(sf: Double, tf: Double, sfStr: String, tfStr: String) {
        // given
        val metric = "metric_name[5m]".asRangeVector().holtWinters(sf, tf)

        // then
        metric.asString() shouldBeEqualTo  "holt_winters(metric_name[5m], $sfStr, $tfStr)"
    }

    @ParameterizedTest
    @MethodSource("functionInvalidArguments")
    fun `should fail when apply holtWinters function with invalid arguments`(sf: Double, tf: Double) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            "metric_name[5m]".asRangeVector().holtWinters(sf, tf)
        }
    }

    companion object {
        @JvmStatic
        fun functionValidArguments() = arrayOf(
            arrayOf(0.0, 1.0, "0.0", "1.0"),
            arrayOf(1.0, 0.0, "1.0", "0.0"),
            arrayOf(0.4, 0.5, "0.4", "0.5")
        )
        @JvmStatic
        fun functionInvalidArguments() = arrayOf(
            arrayOf(0.0, 1.1),
            arrayOf(1.1, 0.0),
            arrayOf(-0.1, 0.0),
            arrayOf(0.0, -0.1)
        )

    }
}