package fr.enedis.grafana.dsl.panels.transformations

import fr.enedis.grafana.dsl.panels.transformation.PanelTransformationsBuilder
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.`should not be empty`
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class PanelTransformationReduceBuilderTest {
    @Test
    fun `should create a reduce transformation panel`() {
        val panelTransformationBuilder = PanelTransformationsBuilder()

        with(panelTransformationBuilder) {
            calculateField {
                options {
                    mode = "reduceRow"
                    calculation = "sum"
                    replaceFields = true
                }
            }
            reduce {
                options {
                    mode = "seriesToRows"
                    includeTimeField = false
                    reducers = listOf("diff")
                }
            }
        }

        val panelTransformations = panelTransformationBuilder.createPanelTransformations()
        panelTransformations.`should not be empty`()
        panelTransformations.count().shouldBe(2)
        val panelTransformationsJson = panelTransformations[1].toJson()
        panelTransformationsJson.toString().shouldEqualToJson("""
            {
                "id": "reduce",
                "options": {
                    "includeTimeField": false,
                    "mode": "seriesToRows",
                    "reducers": [
                        "diff"
                    ]
                }
            }
        """)
    }

    @Test
    fun `should create a reduce transformation panel for mode seriesToRows`() {
        val panelTransformationBuilder = PanelTransformationsBuilder()

        with(panelTransformationBuilder) {
            calculateField {
                options {
                    mode = "reduceRow"
                    calculation = "sum"
                    replaceFields = true
                }
            }
            reduce {
                seriesToRows(listOf("diff"), includeTimeField = false)
            }
        }

        val panelTransformations = panelTransformationBuilder.createPanelTransformations()
        panelTransformations.`should not be empty`()
        panelTransformations.count().shouldBe(2)
        val panelTransformationsJson = panelTransformations[1].toJson()
        panelTransformationsJson.toString().shouldEqualToJson("""
            {
                "id": "reduce",
                "options": {
                    "includeTimeField": false,
                    "mode": "seriesToRows",
                    "reducers": [
                        "diff"
                    ]
                }
            }
        """)
    }
}