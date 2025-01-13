/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard.link

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Mutable builder for building links collection.
 */
@DashboardElement
class DashboardLinksBuilder {
    internal val links: MutableList<DashboardLink> = mutableListOf()

    /**
     * Adds new link to [links] with given [title].
     */
    fun link(title: String, build: DashboardLinkBuilder.() -> DashboardLink) {
        links += DashboardLinkBuilder(title).build()
    }
}