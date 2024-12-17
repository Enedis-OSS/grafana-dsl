package fr.enedis.grafana.dsl.panels.transformations

import fr.enedis.grafana.dsl.panels.transformation.PanelTransformationsBuilder
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.`should not be empty`
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class PanelTransformationFilterTest {
    @Test
    fun `should not create a filter when no filter is declared`() {
        val panelTransformationBuilder = PanelTransformationsBuilder()

        with(panelTransformationBuilder) {
            calculateField {
                options {
                    mode = "reduceRow"
                    calculation = "sum"
                    replaceFields = true
                    alias = "Functional value"
                }
            }
        }

        val panelTransformations = panelTransformationBuilder.createPanelTransformations()
        panelTransformations.`should not be empty`()
        val panelTransformationsJson = panelTransformations[0].toJson()
        panelTransformationsJson.has("options").shouldBe(true)
        panelTransformationsJson.has("filter").shouldBe(false)
    }

    @Test
    fun `should create the right filter when a filter is declared`() {
        val panelTransformationBuilder = PanelTransformationsBuilder()

        with(panelTransformationBuilder) {
            calculateField {
                filter {
                    id = "byRefId"
                    options = "A"
                }
                options {
                    mode = "reduceRow"
                    calculation = "sum"
                    replaceFields = true
                    alias = "Functional value"
                }
            }
        }

        val panelTransformations = panelTransformationBuilder.createPanelTransformations()
        panelTransformations.`should not be empty`()
        val panelTransformationsJson = panelTransformations[0].toJson()
        panelTransformationsJson.has("options").shouldBe(true)
        panelTransformationsJson.has("filter").shouldBe(true)
        panelTransformationsJson.getJSONObject("filter").toString().shouldEqualToJson(
            """{ "id": "byRefId", "options": "A" }"""
        )
    }

    @Test
    fun `should create the right filter when a byRefId filter is declared`() {
        val panelTransformationBuilder = PanelTransformationsBuilder()

        with(panelTransformationBuilder) {
            calculateField {
                byRefId("A")
                options {
                    mode = "reduceRow"
                    calculation = "sum"
                    replaceFields = true
                    alias = "Functional value"
                }
            }
        }

        val panelTransformations = panelTransformationBuilder.createPanelTransformations()
        panelTransformations.`should not be empty`()
        val panelTransformationsJson = panelTransformations[0].toJson()
        panelTransformationsJson.has("options").shouldBe(true)
        panelTransformationsJson.has("filter").shouldBe(true)
        panelTransformationsJson.getJSONObject("filter").toString().shouldEqualToJson(
            """{ "id": "byRefId", "options": "A" }"""
        )
    }
}