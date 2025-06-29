/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

object Alerting : AlertingState {
    override fun asState() = "alerting"
}

object Ok : AlertingState {
    override fun asState() = "ok"
}

object KeepLastState : AlertingState {
    override fun asState() = "keep_state"
}

object NoData : AlertingState {
    override fun asState() = "no_data"
}