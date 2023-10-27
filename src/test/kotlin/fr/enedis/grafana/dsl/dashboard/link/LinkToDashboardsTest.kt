package fr.enedis.grafana.dsl.dashboard.link

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class LinkToDashboardsTest {

    @Test
    fun `should create and serialize minimal working url link`() {
        val builder = DashboardLinkBuilder("Test link")
        val toDashboards = builder.dashboardsWithTags("tag1", "tag2")

        toDashboards.toJson().toString() shouldEqualToJson jsonFile("LinkToDashboardsMinimal.json")
    }
}