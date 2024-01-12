package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.panels.imageIt.ImageItDisplayOptions

class AlertDisplayOptionsBuilder {
    var showOptions: ShowOptions = ShowOptions.CURRENT
    var maxItems: Int = 10
    var sortOrder: SortOrder = SortOrder.TIME_DESC
    var dashboardAlerts: Boolean = false
    var alertNameFilter: String = ""
    var dashboardTitleFilter: String = ""
    var folderId: Long? = null
    var stateFilter: AlertPanelStateFilter = AlertPanelStateFilter()

    fun createAlertDisplayOptions(): AlertPanelDisplayOptions = AlertPanelDisplayOptions(
        showOptions = showOptions,
        maxItems = maxItems,
        sortOrder = sortOrder,
        dashboardAlerts = dashboardAlerts,
        alertNameFilter = alertNameFilter,
        dashboardTitleFilter = dashboardTitleFilter,
        folderId = folderId,
        stateFilter = stateFilter,
    )
}