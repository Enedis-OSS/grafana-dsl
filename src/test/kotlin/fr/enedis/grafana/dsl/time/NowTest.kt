/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.time

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class NowTest {

    @Test
    fun `should stringify correctly`() {
        // expect
        now.toJson() shouldBeEqualTo "now"
        now.toString() shouldBeEqualTo "now"
    }

    @Test
    fun `should create new timestamp with minus used`() {
        // when
        val timestamp = now - 1.w

        // then
        timestamp.toJson() shouldBeEqualTo "now-1w"
        timestamp.toString() shouldBeEqualTo "now-1w"
    }
}