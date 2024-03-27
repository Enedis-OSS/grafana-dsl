package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.datasource.Grafana
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.timeSeries.*
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.Test

class TimeSeries {


    @Test
    fun `should create time series`() {
        val expectedDashboard = dashboard("time series test") {
            panels {
                timeSeriesPanel("time series panel") {
                    metrics(Grafana) {}
                    fieldConfig {
                        custom {
                            drawStyle = DrawStyle.LINE
                            gradientMode = GradientMode.HUE
                            lineStyle = LineStyle(fill = LineStyleFill.DOT, listOf(10, 20))
                            insertNulls = SpanNulls(connectNullValue = ConnectNullValue.THRESHOLD)
                        }
                        overrides {
                            byName("200") {
                                properties {
                                    id = "color"
                                    value = mapOf(
                                        "fixedColor" to Color.RED.asRgbaString(),
                                        "mode" to "fixed"
                                    )
                                }
                            }
                            byValue(
                                mapOf(
                                    "reducer" to "firstNotNull",
                                    "op" to "lte",
                                    "value" to 3
                                )
                            ) {
                                colorScheme(color = Color.RED)
                            }
                            override {
                                matcher {
                                    id = "byName"
                                    options = "200"
                                }
                                properties {
                                    id = "color"
                                    value = mapOf(
                                        "fixedColor" to Color.RED.asRgbaString(),
                                        "mode" to "fixed"
                                    )
                                }
                            }
                        }
                    }
                    options {
                        tooltip = TimeSeriesTooltip(
                            mode = TooltipDisplayMode.MULTI,
                            sort = SortOrder.DESC,
                            maxWidth = 44
                        )
                        legend {
                            mode = LegendMode.TABLE
                            placement = LegendPlacement.BOTTOM
                            isVisible = true
                            showLegend = true
                            sortDesc = false
                            width = 21
                            calcs = listOf("last", "first")

                        }

                    }
                }
            }
        }
        expectedDashboard.toString() shouldEqualToJson jsonFile("TimeSeries.json")
    }
}