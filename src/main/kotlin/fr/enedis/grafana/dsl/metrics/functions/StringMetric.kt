package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

class StringMetric(private val metric: String) : Metric {
    override fun asString() = metric
}
