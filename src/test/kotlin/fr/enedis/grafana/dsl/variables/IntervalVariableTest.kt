/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.time.h
import fr.enedis.grafana.dsl.time.m
import org.junit.jupiter.api.Test

class IntervalVariableTest {

    @Test
    fun `should create variable with all properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.interval(1.m, 10.m, 1.h) {
            displayName = "Test"
            hidingMode = HidingMode.NONE
            auto = true
            stepCount = 10
            minInterval = 10.m
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("IntervalVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.interval(1.m, 10.m, 1.h) {
            auto = false
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("IntervalVariableWithMinProps.json")
    }
}