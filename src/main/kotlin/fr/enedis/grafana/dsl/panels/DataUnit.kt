package fr.enedis.grafana.dsl.panels

enum class DataUnit(val unit: String) {
    SHORT("short"),
    BYTES("bytes"),
    DECIMAL_BYTES("decbytes"),
    SECONDS("s"),
    MILLISECONDS("ms"),
    MICROSECONDS("µs"),
    DURATION_MS("dtdurationms"),
    PERCENT_0_1("percentunit"),
    PERCENT_0_100("percent"),
    BYTES_PER_SECOND("Bps"),
    NONE("none"),
}