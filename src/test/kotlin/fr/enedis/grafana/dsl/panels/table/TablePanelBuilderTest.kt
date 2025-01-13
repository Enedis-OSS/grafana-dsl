/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.table

import fr.enedis.grafana.dsl.datasource.PromQl
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.metrics.functions.*
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import fr.enedis.grafana.dsl.metrics.prometheus.operators.div
import fr.enedis.grafana.dsl.metrics.prometheus.operators.min
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.time.h
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class TablePanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create table panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("EmptyTablePanel.json")
    }

    @Test
    fun `should create table panel with custom bounds`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            bounds = 12 to 8
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanelWithCustomBounds.json")
    }

    @Test
    fun `should create table panel with custom data source`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            datasource = Zabbix
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanelWithCustomDataSource.json")
    }

    @Test
    fun `should create table panel with referenced metrics`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            metrics {
                metric("A", hidden = true) {
                    "*.*.service.processes.system.session-query.*.*.unknown_session.succeeded.count"
                        .groupByNodes("sum", 7).summarize(24.h, "sum").aliasByNode(0)
                }
                metric("B", hidden = true) {
                    "*.*.service.processes.system.session-query.*.*.*.succeeded.count"
                        .groupByNodes("sum", 7).summarize(24.h, "sum").aliasByNode(0)
                }
                metric {
                    ("#A" scale 100.0).group("#B").groupByNode(0, "divideSeries")
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanelWithMetrics.json")
    }

    @Test
    fun `should create table panel with custom relative time`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            timeFrom = 1.h
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanelWithCustomTimeShift.json")
    }

    @Test
    fun `should create table panel with custom columns`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            timeFrom = 1.h
            columns {
                "custom" to "Custom"
                "first" to "First"
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanelWithColumns.json")
    }

    @Test
    fun `should create full table panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            timeFrom = 1.h

            metrics {
                metric("A", hidden = true) {
                    "*.*.service.processes.system.session-query.*.*.unknown_session.succeeded.count"
                        .groupByNodes("sum", 7).summarize(24.h, "sum").aliasByNode(0)
                }
                metric("B", hidden = true) {
                    "*.*.service.processes.system.session-query.*.*.*.succeeded.count"
                        .groupByNodes("sum", 7).summarize(24.h, "sum").aliasByNode(0)
                }
                metric {
                    ("#A" scale 100.0).group("#B").groupByNode(0, "divideSeries")
                }
            }

            columns {
                "current" to "Current"
            }

            styles {
                style("Current") {
                    type = ColumnStyleType.NUMBER
                    unit = DataUnit.PERCENT_0_100
                    decimals = 2
                }
                style("/.*/") {
                    type = ColumnStyleType.HIDDEN
                    decimals = 2
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanel.json")
    }


    @Test
    fun `should create table panel for prometheus`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            val usedMemory = """jvm_memory_used_bytes{area="heap"}""".asInstantVector()
            val maxMemory = """jvm_memory_max_bytes{area="heap"}""".asInstantVector()

            metrics(PromQl) {
                prometheusMetric("{{instance}}") {
                    (usedMemory / maxMemory).min(by = listOf("instance"))
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("TablePanelWithPrometheusMetrics.json")
    }

    @Test
    fun `should create table panel with field config`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            fieldConfig {
                thresholds(ThresholdMode.ABSOLUTE) {
                    steps {
                        "0" to Color.RED
                    }
                }
                custom {
                    align = "auto"
                    inspect = false
                    cellOptionsType = "auto"
                }
                mappings {
                }
                overrides {
                    byName("Size en bytes") {
                        properties {
                            id = "unit"
                            value = "bytes"
                        }
                        valueMapping("toto" to "tata")
                        hide()
                    }
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        println(panels[0].toJson().toString())
        panels[0].toJson().toString() shouldEqualToJson jsonFile("FieldConfigTablePanel.json")
    }
}