/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard.link

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Mutable builder of basic link information.
 *
 * Used for supplying such basic information to concrete annotation builders (see [LinkToUrl.Builder]).
 */
@DashboardElement
class DashboardLinkBuilder(val title: String)