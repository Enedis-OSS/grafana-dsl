package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.DashboardElement
import fr.enedis.grafana.dsl.metrics.ReferencedDashboardMetric
import fr.enedis.grafana.dsl.time.Duration
import fr.enedis.grafana.dsl.time.now

@DashboardElement
class ConditionBuilder {

    internal val conditions = mutableListOf<AlertingCondition>()

    fun query(metric: ReferencedDashboardMetric, duration: Duration) = AlertQuery(
            metric = metric,
            params = *arrayOf(metric.id, duration.toString(), now.toString())
    )

    fun query(metricId: String, duration: Duration) = AlertQuery(
        params = *arrayOf(metricId, duration.toString(), now.toString())
    )

    infix fun AlertQuery.isAbove(value: Int) = QueryCondition(evaluator = AlertEvaluator("gt", value), query = this)

    infix fun AlertQuery.isBelow(value: Int) = QueryCondition(evaluator = AlertEvaluator("lt", value), query = this)

    fun AlertQuery.isOutsideRange(from: Int, to: Int) = QueryCondition(evaluator = AlertEvaluator("outside_range", from, to), query = this)

    fun AlertQuery.isInsideRange(from: Int, to: Int) = QueryCondition(evaluator = AlertEvaluator("inside_range", from, to), query = this)

    fun sum(condition: AlertingCondition) = SumCondition(condition)

    fun max(condition: AlertingCondition) = MaxCondition(condition)

    fun min(condition: AlertingCondition) = MinCondition(condition)

    fun avg(condition: AlertingCondition) = AvgCondition(condition)

    fun last(condition: AlertingCondition) = LastCondition(condition)

    infix fun AlertingCondition.and(condition: AlertingCondition): AlertingCondition {
        conditions += this
        return AndCondition(condition)
    }

    infix fun AlertingCondition.or(condition: AlertingCondition): AlertingCondition {
        conditions += this
        return OrCondition(condition)
    }
}
