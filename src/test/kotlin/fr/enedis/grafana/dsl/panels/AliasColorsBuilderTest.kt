/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.panels.Color.Companion.GREEN
import fr.enedis.grafana.dsl.panels.Color.Companion.RED
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * [AliasColors] and [AliasColorsBuilder] Test
 */
class AliasColorsBuilderTest {

    @Test
    fun `should create not empty alias colors block`() {

        val builder = AliasColorsBuilder().apply {
            "success" to GREEN
            "fail" to RED
        }

        builder.aliasColors.toJson().toString() shouldBeEqualTo """{"fail":"#bf1b00","success":"#7eb26d"}"""
    }

    @Test
    fun `should create empty alias colors block`() {

        AliasColorsBuilder().aliasColors.toJson().toString() shouldBeEqualTo """{}"""
    }
}