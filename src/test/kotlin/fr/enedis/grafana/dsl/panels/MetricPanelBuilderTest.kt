/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.datasource.PromQl
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.json.set
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.metrics.functions.aliasByNode
import fr.enedis.grafana.dsl.metrics.functions.asPercent
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import fr.enedis.grafana.dsl.metrics.prometheus.operators.div
import fr.enedis.grafana.dsl.metrics.prometheus.operators.min
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBe
import org.json.JSONObject
import org.junit.jupiter.api.Test

class MetricPanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create metric panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("EmptyMetricPanel.json")
    }

    @Test
    fun `should create metric panel with custom bounds`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            bounds = 12 to 6
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithCustomBounds.json")
    }

    @Test
    fun `should create metric panel with custom data source`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            datasource = Zabbix
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithCustomDataSource.json")
    }

    @Test
    fun `should create metric panel with additional properties`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            properties {
                it["legend"] = JSONObject()
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithAdditionalProperties.json")
    }

    @Test
    fun `should create metric panel with 2 metrics`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            metrics {
                metric { "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 0 }
                metric { "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 1 }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithMetrics.json")
    }

    @Test
    fun `should create metric panel with as percent metric on metric`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            metrics {
                val refMetric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count1" aliasByNode 0
                metric {
                    "*.*.oil-gate.requests.incoming.*.*.process_time.*.count2" aliasByNode 1 asPercent refMetric
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithAsPercentMetric.json")
    }

    @Test
    fun `should create metric prometheus percent metric`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.metricPanel(title = "Test Panel") {
            metrics(PromQl) {
                val usedMemory = """jvm_memory_used_bytes{area="heap"}""".asInstantVector()
                val maxMemory = """jvm_memory_max_bytes{area="heap"}""".asInstantVector()

                prometheusMetric(legendFormat = "instance") {
                    (usedMemory / maxMemory).min(by = listOf("instance"))
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("MetricPanelWithPercentPrometheusMetric.json")
    }
}