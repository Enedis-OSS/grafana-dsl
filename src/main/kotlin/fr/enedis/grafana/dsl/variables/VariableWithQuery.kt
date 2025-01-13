/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Variable with **query** property.
 *
 * It's recommended to use instances of this class as delegates for other variable implementations.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class VariableWithQuery(private val delegate: Variable, private val query: String) : Variable by delegate {

    override fun toJson() = jsonObject(delegate.toJson()) {
        "query" to query
    }
}