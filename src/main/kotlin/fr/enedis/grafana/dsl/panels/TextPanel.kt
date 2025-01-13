/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.json.emptyJsonArray
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * The text panel lets you make information and description panels etc.
 * @author Aleksandr Korkin
 * @since 27.09.2019
 */
class TextPanel(
    private val basePanel: Panel,
    private val content: String,
    private val transparent: Boolean = false,
    private val mode: ContentMode = ContentMode.MARKDOWN

) : Panel {
    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "text"
        "links" to emptyJsonArray()
        "transparent" to transparent
        "content" to content
        "mode" to mode.value
    }
}