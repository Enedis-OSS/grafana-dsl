/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator

class BasePanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
            BasePanel(
                id = panelLayoutGenerator.nextId(),
                title = title,
                position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
            ),
            propertiesSetter
    )
}