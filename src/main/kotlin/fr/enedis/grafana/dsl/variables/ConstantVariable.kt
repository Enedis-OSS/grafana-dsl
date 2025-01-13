/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import fr.enedis.grafana.dsl.DashboardElement

/**
 * Constant that can be reused in dashboard.
 *
 * @param name see [BaseVariable.name]
 * @param displayName see [BaseVariable.displayName]
 * @param hidingMode see [BaseVariable.hidingMode]
 * @param value value of constant
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class ConstantVariable(
    name: String,
    displayName: String?,
    hidingMode: HidingMode,
    value: String
) : Variable by VariableWithValues(
    delegate = VariableWithQuery(
        delegate = BaseVariable(
            name = name,
            displayName = displayName,
            hidingMode = hidingMode,
            type = "constant"
        ),
        query = value
    ),
    values = listOf(VariableValue(value))
) {

    /**
     * Mutable builder that used for setting up unnecessary properties of [ConstantVariable].
     */
    @DashboardElement
    class Builder {

        var displayName: String? = null

        var hidingMode: HidingMode = HidingMode.NONE
    }
}