/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel

class AlertPanel(
    private val basePanel: Panel,
    private val options: AlertPanelDisplayOptions = AlertPanelDisplayOptions(),
) : Panel {
    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "alertlist"
        "options" to options
    }
}