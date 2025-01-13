/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class SeriesOverrideTest {

    @Test
    fun `should create custom series override`() {

        val seriesOverride = SeriesOverride(
                alias = "total",
                bars = false,
                lines = true,
                stack = false,
                lineWidth = 2
        )
        seriesOverride.toJson().toString() shouldEqualToJson jsonFile("CustomSeriesOverride.json")
    }
}