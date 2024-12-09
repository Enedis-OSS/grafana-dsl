@file:Suppress("TooManyFunctions")
package fr.enedis.grafana.dsl.metrics.functions

import fr.enedis.grafana.dsl.metrics.Metric
import fr.enedis.grafana.dsl.variables.Variable

/**
 * Takes one [metric] followed by an integer [n] and an aggregation function. Out of all metrics passed, draws only
 * the [n] metrics with the highest aggregated value over the time period specified.
 *
 * For example, `"server*.instance*.threads.busy" highestCurrent 5` draws the 5 servers with the highest busy threads.
 *
 * @author Dmitry Komarov
 * @since 20.03.2019
 */
class Highest internal constructor(
    private val metric: Metric,
    private val n: String,
    aggregation: String
) : Metric {

    private val highestFunction: String = when (aggregation) {
        "max" -> "highestMax"
        "average" -> "highestAverage"
        "current" -> "highestCurrent"
        else -> throw IllegalArgumentException("Unknown aggregation: aggregation=$aggregation")
    }

    override fun asString(): String = "$highestFunction(${metric.asString()}, $n)"
}

infix fun Metric.highestCurrent(n: Int): Metric = Highest(this, n.toString(), "current")

infix fun String.highestCurrent(n: Int): Metric = StringMetric(this) highestCurrent n

infix fun Metric.highestCurrent(variable: Variable): Metric = Highest(this, variable.asVariable(), "current")

infix fun String.highestCurrent(variable: Variable): Metric = StringMetric(this) highestCurrent variable

infix fun Metric.highestMax(n: Int): Metric = Highest(this, n.toString(), "max")

infix fun String.highestMax(n: Int): Metric = StringMetric(this) highestMax n

infix fun Metric.highestMax(variable: Variable): Metric = Highest(this, variable.asVariable(), "max")

infix fun String.highestMax(variable: Variable): Metric = StringMetric(this) highestMax variable

infix fun Metric.highestAverage(n: Int): Metric = Highest(this, n.toString(), "average")

infix fun String.highestAverage(n: Int): Metric = StringMetric(this) highestAverage n

infix fun Metric.highestAverage(variable: Variable): Metric = Highest(this, variable.asVariable(), "average")

infix fun String.highestAverage(variable: Variable): Metric = StringMetric(this) highestAverage variable
