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
        println(expectedDashboard)
        expectedDashboard.toString() shouldEqualToJson jsonFile("TimeSeries.json")
    }
}