/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.repeat.Horizontal
import fr.enedis.grafana.dsl.panels.repeat.Vertical
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class RepeatBuilderTest {

    @Test
    fun `should create repeat horizontal`() {

        val expectedDashboard = dashboard("repeat horizontal") {

            val hosts by variables.custom("host1", "host2")

            panels {
                singleStat("repeat horizontal") {
                    repeat(hosts) {
                        direction = Horizontal(2)
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("RepeatBuilderHorizontal.json")
    }

    @Test
    fun `should create repeat vertical`() {

        val expectedDashboard = dashboard("repeat vertical") {

            val hosts by variables.custom("host1", "host2")

            panels {
                singleStat("repeat vertical") {
                    repeat(hosts) {
                        direction = Vertical()
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("RepeatBuilderVertical.json")
    }
}