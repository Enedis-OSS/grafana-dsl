/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.DashboardElement
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator

/**
 * Builder for panels containers, for example: strings.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
@DashboardElement
interface PanelContainerBuilder {

    val panels: MutableList<Panel>
    val panelLayoutGenerator: PanelLayoutGenerator
}