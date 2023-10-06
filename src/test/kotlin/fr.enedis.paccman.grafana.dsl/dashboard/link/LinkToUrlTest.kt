package fr.enedis.grafana.dsl.dashboard.link

import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson

class LinkToUrlTest {

    @Test
    fun `should create and serialize minimal working url link`() {
        val builder = DashboardLinkBuilder("Test link")
        val toUrl = builder.url("http://test.url")

        toUrl.toJson().toString() shouldEqualToJson jsonFile("LinkToUrlMinimal.json")
    }
}