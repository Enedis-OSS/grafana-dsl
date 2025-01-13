/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.variables.Variable

/**
 * Panels builder.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class PanelsBuilder(override val panelLayoutGenerator: PanelLayoutGenerator) : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>()

    /**
     * This method is used to support backward compatibility.
     */
    fun row(title: String, build: RowBuilder.() -> Unit) {
        row(title, null, false, build)
    }

    fun row(title: String, repeatFor: Variable? = null, collapsed: Boolean = false, build: RowBuilder.() -> Unit) {
        val builder = RowBuilder(title, repeatFor?.name, panelLayoutGenerator, collapsed)
        builder.build()
        panels += builder.createRow()
    }
}