package fr.enedis.grafana.dsl.generator

import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.panel
import fr.enedis.grafana.dsl.shouldEqualToJson

class SimplePanelLayoutGeneratorTest {

    @Test
    fun `test generator`() {
        val dashboard = dashboard("test generator") {
            panels {

                panel("1") {}

                panel("2") {}

                panel("3") {}

                row("4") {

                    panel("5") {}

                    panel("6") {}
                }
            }
        }

        dashboard.toString() shouldEqualToJson jsonFile("SimpleGenerator.json")
    }
}