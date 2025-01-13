/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class TextBoxVariableTest {

    @Test
    fun `should create variable with all properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.textBox("test") {
            displayName = "Test"
            hidingMode = HidingMode.LABEL
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("TextBoxVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.textBox()

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("TextBoxVariableWithMinProps.json")
    }
}