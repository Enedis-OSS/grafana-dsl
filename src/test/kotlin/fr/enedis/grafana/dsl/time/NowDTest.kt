/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.time

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * [nowD] Test
 */
class NowDTest {

    @Test
    fun `should stringify correctly`() {
        // expect
        nowD.toJson() shouldBeEqualTo "now/d"
        nowD.toString() shouldBeEqualTo "now/d"
    }
}