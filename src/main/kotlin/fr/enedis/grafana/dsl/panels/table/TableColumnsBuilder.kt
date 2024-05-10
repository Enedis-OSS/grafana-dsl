package fr.enedis.grafana.dsl.panels.table

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Table column builder
 * @author Aleksandr Korkin
 * @since 16.12.2019
 */
@DashboardElement
class TableColumnsBuilder {
    val columns = mutableListOf<TableColumn>()
    infix fun String.to(name: String) {
        columns += TableColumn(name = name, value = this)
    }
}
