package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.metrics.ReferencedDashboardMetric
import fr.enedis.grafana.dsl.metrics.functions.sumSeries
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

/**
 * Тест для [fr.enedis.grafana.dsl.panels.alerting.MaxCondition]
 * @author abramovgerman
 * @since 02.12.2020
 */
class MaxConditionTest {
    @Test
    fun `should create max alerting condition`() {
        // given
        val metric = "*.*.count".sumSeries()
        val maxCondition = MaxCondition(
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
        maxCondition.toJson().toString() shouldEqualToJson jsonFile("MaxCondition.json")
    }
}