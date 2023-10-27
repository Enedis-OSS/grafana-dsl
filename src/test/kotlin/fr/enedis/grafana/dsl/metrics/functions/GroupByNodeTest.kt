package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GroupByNodeTest {

    @Test
    fun `should create metric`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNode(0, "avg")

        // then
        metric.asString() shouldBeEqualTo "groupByNode(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 0, 'avg')"
    }

    @Test
    fun `should create metric with default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" groupByNode 1

        // then
        metric.asString() shouldBeEqualTo "groupByNode(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 1, 'sum')"
    }
}