package fr.enedis.grafana.dsl.dashboard.link

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class LinkToUrlTest {

    @Test
    fun `should create and serialize minimal working url link`() {
        val builder = DashboardLinkBuilder("Test link")
        val toUrl = builder.url("http://test.url")

        toUrl.toJson().toString() shouldEqualToJson jsonFile("LinkToUrlMinimal.json")
    }
}