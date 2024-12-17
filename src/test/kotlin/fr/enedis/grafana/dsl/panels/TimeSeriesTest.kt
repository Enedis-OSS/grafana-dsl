package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.datasource.Grafana
import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.timeSeries.*
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.time.m
import org.amshove.kluent.*
import org.junit.Test
import org.json.JSONArray as JSONArray

class TimeSeriesTest {


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

    @Test
    fun `should create a time series without interval`() {
        val expectedDashboard = dashboard("time series test") {
            panels {
                timeSeriesPanel("time series panel") {

                }
            }
        }

        val expectedJson = expectedDashboard.toJson()
        expectedJson.keys().asSequence().shouldContain("panels")
        expectedJson.has("panels").shouldBe(true)
        expectedJson.getJSONArray("panels").length().shouldBe(1)
        expectedJson.getJSONArray("panels").getJSONObject(0).has("interval").shouldBe(false)
    }

    @Test
    fun `should create a time series with interval`() {
        val expectedDashboard = dashboard("time series test") {
            panels {
                timeSeriesPanel("time series panel") {
                    interval = 2.m
                }
            }
        }

        val expectedJson = expectedDashboard.toJson()
        expectedJson.keys().asSequence().shouldContain("panels")
        expectedJson.has("panels").shouldBe(true)
        expectedJson.getJSONArray("panels").length().shouldBe(1)
        expectedJson.getJSONArray("panels").getJSONObject(0).has("interval").shouldBe(true)
        expectedJson.getJSONArray("panels").getJSONObject(0).getString("interval").shouldBeEqualTo("2m")
    }
}