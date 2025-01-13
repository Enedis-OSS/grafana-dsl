/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.generator

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.panel
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

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