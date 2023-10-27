package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.metrics.ReferencedDashboardMetric
import fr.enedis.grafana.dsl.metrics.functions.sumSeries
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

/**
 * Tests for [fr.enedis.grafana.dsl.panels.alerting.MinCondition]
 */
class MinConditionTest {
    @Test
    fun `should create min alerting condition`() {
        // given
        val metric = "*.*.count".sumSeries()
        val minCondition = MinCondition(
                QueryCondition(
                        evaluator = AlertEvaluator("gt", 100),
                        query = AlertQuery(
                                metric = ReferencedDashboardMetric(
                                        metric = metric,
                                        id = "1",
                                        hidden = true
                                )
                        )
                )
        )

        // then
        minCondition.toJson().toString() shouldEqualToJson jsonFile("MinCondition.json")
    }
}