/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl

import fr.enedis.grafana.dsl.dashboard.Dashboard
import fr.enedis.grafana.dsl.dashboard.DashboardBuilder

annotation class KDashboard

annotation class KTag(val value: String)

annotation class KTags(val value:Array<String>)

/**
 * Returns JSON-string for dashboard import.
 *
 * @param title Dashboard title
 * @param build Dashboard builder closure
 * @return JSON-string
 */
fun dashboard(title: String, build: DashboardBuilder.() -> Unit): Dashboard {
    val builder = DashboardBuilder(title)
    builder.build()
    return builder.createDashboard()
}