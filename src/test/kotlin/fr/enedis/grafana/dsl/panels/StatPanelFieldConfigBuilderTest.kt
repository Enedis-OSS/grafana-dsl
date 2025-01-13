/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.stat.statPanel
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.variables.RefreshMode
import org.junit.jupiter.api.Test

/**
 * @author Aleksey Matveev
 * @since 05.11.2020
 */
class StatPanelFieldConfigBuilderTest {
    @Test
    fun `should create value mapping`() {
        val expectedDashboard = dashboard("value mapping") {
            val hosts by variables.query(datasource = Zabbix, query = "Skrat Back.*") {
                regex = ".*acquirer.*"
                refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE
                includeAllValue = true
            }
            panels {
                statPanel(hosts.asVariable()) {
                    fieldConfig {
                        mappings {
                            valueToText {
                                "null" to "N/A"
                            }
                        }
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("MappingsBuilderValueToText.json")
    }

    @Test
    fun `should create range mapping`() {
        val expectedDashboard = dashboard("range mapping") {
            val hosts by variables.query(datasource = Zabbix, query = "Skrat Back.*") {
                regex = ".*acquirer.*"
                refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE
                includeAllValue = true
            }
            panels {
                statPanel(hosts.asVariable()) {
                    fieldConfig {
                        mappings {
                            rangeToText {
                                range("0", "100", "0-100")
                            }
                        }
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("MappingsBuilderRangeToText.json")
    }
}