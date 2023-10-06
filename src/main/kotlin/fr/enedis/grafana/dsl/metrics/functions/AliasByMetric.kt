package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric

/**
 * AliasByMetric graphite function. Takes a seriesList and applies an alias derived from the base metric name.
 *
 * [aliasByMetric](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.aliasByMetric)
 *
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class AliasByMetric internal constructor(private val seriesList: Metric) : Metric {
    override fun asString() = "aliasByMetric(${seriesList.asString()})"
}

fun String.aliasByMetric() = AliasByMetric(StringMetric(this))

fun Metric.aliasByMetric() = AliasByMetric(this)
