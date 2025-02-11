/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.transformations

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.TestContainerBuilder
import fr.enedis.grafana.dsl.panels.table.tablePanel
import fr.enedis.grafana.dsl.panels.transformation.FilterFieldsByValueFilter
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class PanelTransformationsBuilderTest {

    @Test
    fun `should create table panel with group by transformations`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            transformations {
                groupBy {
                    options {
                        field("@timestamp_second") {
                            aggregations = arrayOf("first")
                            operation = "aggregate"
                        }
                        field("meta.environment") {
                            aggregations = arrayOf("first")
                            operation = "aggregate"
                        }
                        field("meta.file") {
                            operation = "groupby"
                        }
                        field("value_numeric") {
                            aggregations = arrayOf("first")
                            operation = "aggregate"
                        }
                        field("what") {
                            aggregations = arrayOf("allValues")
                        }
                    }
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        println(panels[0].toJson())
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GroupByTransformation.json")
    }

    @Test
    fun `should create table panel with sort by transformations`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            transformations {
                sortBy {
                    options {
                        sortByField(desc = true, field = "File name")
                    }
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        println(panels[0].toJson())
        panels[0].toJson().toString() shouldEqualToJson jsonFile("SortByTransformation.json")
    }

    @Test
    fun `should create table panel with organize transformations`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            transformations {
                organizeField {
                    options {
                        excludeByName to {}
                        includeByName to {}
                        indexByName = mapOf(
                            "meta.environment (first)" to 0,
                            "meta.file" to 1,
                            "value_numeric (first)" to 2
                        )
                        renameByName = mapOf(
                            "meta.environment" to "Environment name",
                            "meta.file" to "File name",
                            "value_numeric" to "Bytes size"
                        )
                    }
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("OrganizeTransformation.json")
    }

    @Test
    fun `should create table panel with filterFieldsByName transformations`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            transformations {
                filterFieldsByName {
                    options {
                        includeField("meta.file")
                        includeField("value_numeric (first)")
                        includeField("meta.environment (first)")
                    }
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("FilterFieldsByNameTransformation.json")
    }

    @Test
    fun `should create table panel with filterFieldsByValue transformations`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            transformations {
                filterFieldsByValue {
                    options {
                        filters = listOf(
                            FilterFieldsByValueFilter(
                                fieldName = "@hour_utc",
                                id = "regex",
                                value = "^2"
                            )
                        )
                    }
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("FilterFieldsByValueTransformation.json")
    }
}