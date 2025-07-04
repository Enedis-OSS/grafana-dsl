/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard.link

/**
 * Type of dashboard's link
 */
enum class DashboardLinkType(val value: String) {
    /**
     * Link to other dashboards
     */
    DASHBOARDS("dashboards"),

    /**
     * Link to arbitrary url address
     */
    URI_LINK("link")
}