/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class TextPanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create empty text panel with MD mode `() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Empty Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/EmptyTextPanel.json")
    }

    @Test
    fun `should create text panel with MD content`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Test Panel MD") {
            content = "# Text \r\n* **panel**"
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/MDTextPanel.json")
    }

    @Test
    fun `should create text panel with HTML content`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Test Panel HTML") {
            content = "<span>Hello</span>"
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/HTMLTextPanel.json")
    }

    @Test
    fun `should create transparent text panel `() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel(title = "Transparent Panel") {
            transparent = true
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/TransparentTextPanel.json")
    }

    @Test
    fun `should create text panel with empty title`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.textPanel() {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("text/TextPanelWithEmptyTitle.json")
    }
}