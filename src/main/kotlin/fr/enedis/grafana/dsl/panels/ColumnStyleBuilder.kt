package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Build columnStyle element
 * @author Aleksandr Korkin
 * @since 16.12.2019
 */
@DashboardElement
class ColumnStyleBuilder {
    val styles = mutableListOf<ColumnStyle>()
    var decimals: Int? = null
    var unit: YAxis.Unit = YAxis.Unit.SHORT
    var alias: String? = null
    var hidden: Boolean = false

    fun style(pattern: String, build: ColumnStyleBuilder.() -> Unit) {
        val builder = ColumnStyleBuilder()
        builder.build()
        styles += ColumnStyle(pattern = pattern, decimals = builder.decimals, unit = builder.unit, alias = builder.alias)
    }
    fun rename(pair: Pair<String, String>) {
        styles += ColumnStyle(pattern = pair.first, alias = pair.second, unit = YAxis.Unit.SHORT)
    }

    fun hide(pattern: String) {
        styles += ColumnStyle(pattern = pattern, unit = YAxis.Unit.SHORT, type = ColumnStyleType.HIDDEN)
    }
}
