/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.time.h
import fr.enedis.grafana.dsl.time.m
import org.junit.jupiter.api.Test

class TimeRangeBuilderTest {

    @Test
    fun `should create time range`() {

        val expectedDashboard = dashboard("time range test") {
            panels {
                singleStat("time range test") {
                    timerange {
                        lastTime = 2.h
                        timeShift = 5.m
                        hideTimeOverrideInfo = true
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("TimeRangeBuilder.json")
    }
}