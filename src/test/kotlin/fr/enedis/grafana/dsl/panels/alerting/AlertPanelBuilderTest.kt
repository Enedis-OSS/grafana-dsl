package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.AbstractPanelTest
import fr.enedis.grafana.dsl.panels.TestContainerBuilder
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class AlertPanelBuilderTest : AbstractPanelTest() {
    @Test
    fun `should create alert panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.alertPanel(title = "Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("EmptyAlertPanel.json")
    }

    @Test
    fun `should create alert panel with custom state filter`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.alertPanel(title = "Test Panel") {
            options {
                stateFilter = AlertPanelStateFilter(ok = true, paused = true)
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("AlertPanelWithCustomStateFilter.json")
    }

}