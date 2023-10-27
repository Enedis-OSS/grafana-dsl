package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * Test for [fr.enedis.grafana.dsl.metrics.functions.AliasSub]
 */
class AliasSubTest {

    @Test
    fun `should create metric that aliased by substring replace (String)`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".aliasSub("(.*.)", "$1 my-metric-name")

        // then
        metric.asString() shouldBeEqualTo "aliasSub(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '(.*.)', '$1 my-metric-name')"
    }

    @Test
    fun `should create metric that aliased by substring replace with search regex (String)`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".aliasSub("(.*oil-gate.)", "my-metric-name $2")

        // then
        metric.asString() shouldBeEqualTo "aliasSub(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '(.*oil-gate.)', 'my-metric-name $2')"
    }
}
