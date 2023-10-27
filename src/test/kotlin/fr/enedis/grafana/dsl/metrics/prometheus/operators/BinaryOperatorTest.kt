package fr.enedis.grafana.dsl.metrics.prometheus.operators

import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class BinaryOperatorTest {

    @Test
    fun `should apply sum binary operator to prometheusMetric`() {
        // given
        val left = "left".asInstantVector()
        val right = "right".asInstantVector()

        // then
        (left + right).asString() shouldBeEqualTo "left + right"
    }

    @Test
    fun `should apply subtraction binary operator to prometheusMetric`() {
        // given
        val left = "left".asInstantVector()
        val right = "right".asInstantVector()

        // then
        (left - right).asString() shouldBeEqualTo "left - right"
    }

    @Test
    fun `should apply multiplication binary operator to prometheusMetric`() {
        // given
        val left = "left".asInstantVector()
        val right = "right".asInstantVector()

        // then
        (left * right).asString() shouldBeEqualTo "left * right"
    }

    @Test
    fun `should apply division binary operator to prometheusMetric`() {
        // given
        val left = "left".asInstantVector()
        val right = "right".asInstantVector()

        // then
        (left / right).asString() shouldBeEqualTo "left / right"
    }

    @Test
    fun `should apply modulo binary operator to prometheusMetric`() {
        // given
        val left = "left".asInstantVector()
        val right = "right".asInstantVector()

        // then
        (left % right).asString() shouldBeEqualTo "left % right"
    }

    @Test
    fun `should apply power binary operator to prometheusMetric`() {
        // given
        val left = "left".asInstantVector()
        val right = "right".asInstantVector()

        // then
        left.pow(right).asString() shouldBeEqualTo "left ^ right"
    }

}