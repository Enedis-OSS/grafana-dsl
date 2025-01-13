/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Variable that represented by TextBox input for user.
 *
 * @param name see [BaseVariable.name]
 * @param displayName see [BaseVariable.displayName]
 * @param hidingMode see [BaseVariable.hidingMode]
 * @param defaultValue value that will be placed as default in textbox on dashboard screen
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class TextBoxVariable(
    name: String,
    displayName: String? = null,
    hidingMode: HidingMode = HidingMode.VARIABLE,
    defaultValue: String
) : Variable by VariableWithQuery(
    delegate = BaseVariable(
        name = name,
        displayName = displayName,
        hidingMode = hidingMode,
        type = "textbox"
    ),
    query = defaultValue
) {

    /**
     * Mutable for builder for setting up unnecessary properties of [TextBoxVariable].
     */
    @DashboardElement
    class Builder {

        var displayName: String? = null

        var hidingMode: HidingMode = HidingMode.NONE
    }
}