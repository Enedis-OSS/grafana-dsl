/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Provider for local variable delegates.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class VariableDelegate(
    private val variables: MutableList<Variable>,
    private val variableFactory: (String) -> Variable
) {

    operator fun provideDelegate(thisRef: Nothing?, property: KProperty<*>): ReadOnlyProperty<Nothing?, Variable> {
        val variable = variableFactory(property.name)
        variables += variable

        return object : ReadOnlyProperty<Nothing?, Variable> {
            override fun getValue(thisRef: Nothing?, property: KProperty<*>) = variable
        }
    }
}