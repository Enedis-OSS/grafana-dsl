package fr.enedis.grafana.dsl.dashboard.link

import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.dashboard.link.DashboardLinkIconShape.EXTERNAL_LINK
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson

class DashboardLinksBuilderTest {

    @Test
    fun `should create dashboard with 3 links`() {
        val dashboard = dashboard(title = "Grafana Links Demo") {

            links {
                link("Test url link") {
                    url("/test") {
                        tooltip = "Open test url link"
                        icon = EXTERNAL_LINK
                        includeVariableValues = true
                        keepTimeRange = true
                        openInNewTab = true
                    }
                }

                link("Test url link") {
                    url("/test") {
                        tooltip = "Open test url link"
                        icon = EXTERNAL_LINK
                        includeVariableValues = true
                        keepTimeRange = true
                        openInNewTab = true
                    }
                }

                link("Test dashboards link") {
                    dashboardsWithTags("my_dashboards", "generated_dashboard") {
                        showAsDropdown = true
                        includeVariableValues = true
                        keepTimeRange = true
                        openInNewTab = true
                    }
                }
            }
        }

        dashboard shouldEqualToJson jsonFile("DashboardLinks.json")
    }
}