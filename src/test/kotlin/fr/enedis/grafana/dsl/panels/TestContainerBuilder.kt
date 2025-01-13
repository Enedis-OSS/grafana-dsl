/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.generators.SimplePanelLayoutGenerator

class TestContainerBuilder(override val panelLayoutGenerator: PanelLayoutGenerator = SimplePanelLayoutGenerator()) : PanelContainerBuilder {
    override val panels = mutableListOf<Panel>()
}