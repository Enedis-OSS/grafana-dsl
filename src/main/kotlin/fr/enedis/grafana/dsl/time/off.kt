/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.time

/**
 * Refresh settings, that disables refresh.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
object off : Refresh {
    override fun asRefreshPeriod() = false
}