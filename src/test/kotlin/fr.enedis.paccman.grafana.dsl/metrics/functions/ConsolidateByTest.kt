package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.AVERAGE
import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.FIRST
import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.LAST
import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.MAX
import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.MIN
import fr.enedis.grafana.dsl.metrics.functions.ConsolidationFunction.SUM

class ConsolidateByTest {

    @Test
    fun `should create metric that consolidate by max function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy MAX

        // then
        metric.asString() shouldEqual "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'max')"
    }

    @Test
    fun `should create metric that consolidate by min function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy MIN

        // then
        metric.asString() shouldEqual "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'min')"
    }

    @Test
    fun `should create metric that consolidate by sum function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy SUM

        // then
        metric.asString() shouldEqual "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'sum')"
    }

    @Test
    fun `should create metric that consolidate by average function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy AVERAGE

        // then
        metric.asString() shouldEqual "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'average')"
    }

    @Test
    fun `should create metric that consolidate by first function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy FIRST

        // then
        metric.asString() shouldEqual "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'first')"
    }

    @Test
    fun `should create metric that consolidate by last function`() {
        // given
        val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy LAST

        // then
        metric.asString() shouldEqual "consolidateBy(*.*.*.requests.incoming.*.*.process_time.*.count, 'last')"
    }
}