package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard.DashboardBuilder
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.SimplePanelLayoutGenerator
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class RowBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create row with 1 panel`() {
        // given
        val panelsBuilder = PanelsBuilder(SimplePanelLayoutGenerator())
        val dashboardBuilder = DashboardBuilder("Test Dashboard")
        val values by dashboardBuilder.variables.query(datasource = Zabbix, query = "My variable") {
            regex = ".*"
        }

        // when
        panelsBuilder.row("Test Row", repeatFor = values) {
            panel("Test Panel") {}
        }
        // then
        val panels = panelsBuilder.panels
        panels.size shouldBe 2
        panels[0].toJson().toString() shouldEqualToJson jsonFile("Row.json")
    }

    @Test
    fun `should create collapsed row with 1 panel`() {
        // given
        val panelsBuilder = PanelsBuilder(SimplePanelLayoutGenerator())

        panelsBuilder.row("Collapsed row", collapsed = true) {
            panel("Test panel") {}
        }
        // then
        val panels = panelsBuilder.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("CollapsedRow.json")
    }
}
