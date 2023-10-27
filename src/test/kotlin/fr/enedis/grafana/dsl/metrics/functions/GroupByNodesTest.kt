package fr.enedis.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GroupByNodesTest {

    //@Test(expectedExceptions = [IllegalArgumentException::class], expectedExceptionsMessageRegExp = "nodes must be not empty")
    @Test
    fun `should throw exception when nodes were not specified`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            // expect exception
            "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNodes("avg")
        }
    }

    @Test
    fun `should create metric`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNodes("avg", 0, 1)

        // then
        metric.asString() shouldBeEqualTo "groupByNodes(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 'avg', 0, 1)"
    }

    @Test
    fun `should create metric with default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNodes(0)

        // then
        metric.asString() shouldBeEqualTo "groupByNodes(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 'sum', 0)"
    }
}