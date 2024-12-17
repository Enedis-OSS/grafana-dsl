package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.panels.stateTimeline.*
import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.datasource.Grafana
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.time.TimeRange
import fr.enedis.grafana.dsl.time.h
import fr.enedis.grafana.dsl.time.m
import fr.enedis.grafana.dsl.time.now
import org.junit.Test

class StateTimelineTest {

    @Test
    fun `should create state timeline`() {
        val expectedDashboard = dashboard("time series test") {
            timeRange = TimeRange(from = now.minus(12.h), to = now)
            refresh = 1.m
            this.uid = "testuid"
            this.tags += listOf("test")
            panels {
                stateTimeline("time series test") {
                    bounds = 6 to 8
                    datasource = Zabbix
                    fieldConfig {
                        colorScheme = ColorScheme(ColorSchemeMode.THRESHOLDS)
                        thresholds(ThresholdMode.ABSOLUTE) {
                            steps {
                                "0" to Color.DARK_RED
                                "1" to Color.GREEN
                                "2" to Color.ORANGE
                                "3" to Color.RED
                            }
                        }
                        mappings {
                            valueToText {
                                "null" to "No Data"
                                "0" to "none"
                                "1" to "juste fine"
                                "2" to "a bit much"
                                "3" to "now that's just gluttony"
                            }
                        }
                    }
                    options { }
                    metrics(Grafana) { }
                }
            }
        }
        expectedDashboard.toString() shouldEqualToJson jsonFile("StateTimeline.json")
    }
}
