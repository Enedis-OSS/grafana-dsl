/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.time.Duration

/**
 * Mutable builder to create variables on dashboard.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class VariablesBuilder {

    /**
     * Variables on dashboard.
     *
     * This field made public to make [VariablesBuilder] extensible.
     */
    val variables = mutableListOf<Variable>()

    /**
     * Creates *interval* variable.
     *
     * @param durations durations that used as values for variable
     * @param build builder function to setting up variable properties
     */
    fun interval(vararg durations: Duration, build: IntervalVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = IntervalVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            IntervalVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                values = durations,
                auto = builder.auto,
                stepCount = builder.stepCount,
                minInterval = builder.minInterval
            )
        }
    }

    /**
     * Creates *query* variable.
     *
     * @param datasource datasource that used to query variable values
     * @param query a string query that will be executed to retrieve variable values
     * @param build builder function to setting up variable properties
     */
    fun query(datasource: Datasource, query: String, build: QueryVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = QueryVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            QueryVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                query = query,
                datasource = datasource,
                refreshMode = builder.refreshMode,
                regex = builder.regex,
                sortOrder = builder.sortOrder,
                multiValuesAllowed = builder.multiValuesAllowed,
                includeAllValue = builder.includeAllValue,
                allValue = builder.allValue,
                tags = builder.tags,
                current = builder.current
            )
        }
    }

    /**
     * Creates *custom* variable.
     *
     * @param values values of variable
     * @param build builder function to setting up variable properties
     */
    fun custom(vararg values: String, build: CustomVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = CustomVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            CustomVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                values = values,
                multiValuesAllowed = builder.multiValuesAllowed,
                includeAllValue = builder.includeAllValue,
                allValue = builder.allValue,
                selectedIndex = builder.selectedIndex
            )
        }
    }

    /**
     * Creates *custom* variable with named option list.
     *
     * @param options options of variable
     * @param build builder function to setting up variable properties
     */
    fun custom(vararg options: VariableValue, build: CustomVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = CustomVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            CustomVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                options = options.asList(),
                multiValuesAllowed = builder.multiValuesAllowed,
                includeAllValue = builder.includeAllValue,
                allValue = builder.allValue,
                selectedIndex = builder.selectedIndex
            )
        }
    }

    /**
     * Creates *constant*.
     *
     * @param value a value of constant
     * @param build builder function to setting up variable properties
     */
    fun constant(value: String, build: ConstantVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = ConstantVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            ConstantVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                value = value
            )
        }
    }

    /**
     * Creates *textBox* variable.
     *
     * @param defaultValue a default value of text box
     * @param build builder function to setting up variable properties
     */
    fun textBox(defaultValue: String = "", build: TextBoxVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = TextBoxVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            TextBoxVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                defaultValue = defaultValue
            )
        }
    }
}