/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.DashboardElement

@DashboardElement
class ThresholdsBuilder {

    internal val thresholds = mutableListOf<Threshold>()

    fun threshold(fn: (ThresholdDsl) -> Threshold) {
        thresholds += fn(ThresholdDsl)
    }
}

object ThresholdDsl {
    infix fun gt(value: Int) = Threshold("gt", value)
}