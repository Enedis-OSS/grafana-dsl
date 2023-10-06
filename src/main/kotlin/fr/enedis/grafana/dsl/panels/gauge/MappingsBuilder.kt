package fr.enedis.grafana.dsl.panels.gauge

/**
 * Builder for mappings tab
 */
class MappingsBuilder {
    val mappings = mutableListOf<Mapping>()

    fun valueToText(build: ValueToTextMapping.Builder.() -> Unit) {
        val builder = ValueToTextMapping.Builder()
        builder.build()
        mappings.addAll(builder.valueToTexts)
    }

    fun rangeToText(build: RangeToTextMapping.Builder.() -> Unit) {
        val builder = RangeToTextMapping.Builder()
        builder.build()
        mappings.addAll(builder.rangeToTexts)
    }
}
