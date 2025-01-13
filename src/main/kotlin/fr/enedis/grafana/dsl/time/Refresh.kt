/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.time

/**
 * Refresh interval.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
interface Refresh {

    /**
     * Returns metric refresh period.
     *
     * @return Metric period
     */
    fun asRefreshPeriod(): Any
}