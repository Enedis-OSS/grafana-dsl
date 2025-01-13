/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

class AlertDisplayOptionsBuilder {
    var showOptions: ShowOptions = ShowOptions.CURRENT
    var maxItems: Int = 10
    var sortOrder: SortOrder = SortOrder.TIME_DESC
    var dashboardAlerts: Boolean = false
    var alertNameFilter: String = ""
    var dashboardTitleFilter: String = ""
    var folderId: Long? = null
    var stateFilter: AlertPanelStateFilter = AlertPanelStateFilter()
    var tags: Collection<String>? = null

    fun createAlertDisplayOptions(): AlertPanelDisplayOptions = AlertPanelDisplayOptions(
        showOptions = showOptions,
        maxItems = maxItems,
        sortOrder = sortOrder,
        dashboardAlerts = dashboardAlerts,
        alertNameFilter = alertNameFilter,
        dashboardTitleFilter = dashboardTitleFilter,
        folderId = folderId,
        stateFilter = stateFilter,
        tags = tags
    )
}