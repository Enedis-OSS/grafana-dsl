/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.datasource

/**
 * Prometheus datasource
 */
interface PrometheusDatasource : Datasource

/**
 * Predefined "PromQL" prometheus datasource
 */
object PromQl : PrometheusDatasource {
    override fun asDatasourceName() = "PromQL"
}

/**
 * Predefined "Prometheus" prometheus datasource
 */
object Prometheus : PrometheusDatasource {
    override fun asDatasourceName() = "Prometheus"
}

/**
 * Create prometheus datasource
 */
fun prometheusDatasource(name: String): PrometheusDatasource = SimplePrometheusDatasource(name)

internal class SimplePrometheusDatasource(private val name: String) : PrometheusDatasource {
    override fun asDatasourceName(): String = name
}