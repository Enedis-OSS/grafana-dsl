package fr.enedis.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.json.set
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson

class BasePanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create base panel`() {
        // given
        val testContainerBuilder = TestContainerBuilder()

        // when
        testContainerBuilder.panel("Test Panel") {}

        // then
        val panels = testContainerBuilder.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("EmptyBasePanel.json")
    }

    @Test
    fun `should create base panel with additional properties`() {
        // given
        val testContainerBuilder = TestContainerBuilder()

        // when
        testContainerBuilder.panel("Test Panel") {
            properties {
                it["type"] = "graph"
            }
        }

        // then
        val panels = testContainerBuilder.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("BasePanelWithAdditionalProperties.json")
    }
}
