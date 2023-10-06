package fr.enedis.grafana.dsl.metrics.functions

enum class ConsolidationFunction(val value: String) {
    SUM("sum"),
    AVERAGE("average"),
    MIN("min"),
    MAX("max"),
    FIRST("first"),
    LAST("last")
}
