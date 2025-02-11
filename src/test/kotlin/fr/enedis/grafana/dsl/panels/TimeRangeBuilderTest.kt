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
import org.junit.jupiter.api.Test

class TimeRangeBuilderTest {

    @Test
    fun `should create time range`() {

        val expectedDashboard = dashboard("time range test") {
            panels {
                singleStat("time range test") {
                    timerange {
                        lastTime = "2h"
                        timeShift = "5m"
                        hideTimeOverrideInfo = true
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("TimeRangeBuilder.json")
    }
}