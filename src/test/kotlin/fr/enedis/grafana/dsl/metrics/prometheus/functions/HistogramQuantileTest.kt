package fr.enedis.grafana.dsl.metrics.prometheus.functions

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class HistogramQuantileTest {

    companion object{
        @JvmStatic
        fun quantiles() = arrayOf(
            arrayOf(0.0, "0.0"),
            arrayOf(0.5, "0.5"),
            arrayOf(1.0, "1.0")
        )
    }

    @ParameterizedTest
    @MethodSource("quantiles")
    fun `should apply histogramQuantile function to prometheusMetric`(quantile: Double, quantilesStr: String) {
        // given
        val metric = "metric_name".asInstantVector().histogramQuantile(quantile)

        // then
        metric.asString() shouldBeEqualTo  "histogram_quantile($quantilesStr, metric_name)"
    }

    @Test
    //@Test(expectedExceptions = [IllegalArgumentException::class],
    //    expectedExceptionsMessageRegExp = "quantile must be greater than or equal to 0 and less than or equal to 1")
    fun `should fail when apply histogramQuantile function with negative quantile`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            "metric_name".asInstantVector().histogramQuantile(-0.01)
        }
    }

    @Test
    //@Test(expectedExceptions = [IllegalArgumentException::class],
    //    expectedExceptionsMessageRegExp = "quantile must be greater than or equal to 0 and less than or equal to 1")
    fun `should fail when apply histogramQuantile function with quantile greater than 1`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            "metric_name".asInstantVector().histogramQuantile(1.01)
        }
    }
}