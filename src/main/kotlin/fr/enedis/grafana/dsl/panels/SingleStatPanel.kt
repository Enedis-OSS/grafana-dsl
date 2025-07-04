/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.repeat.Repeat

/**
 * Singlestat panel presents text from defined metric.
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
@Deprecated("Deprecated in Grafana 7.0")
class SingleStatPanel(
    private val basePanel: Panel,
    private val valueMappings: ValueMappings = ValueMappings(ValueToTextType),
    private val timerange: TimeRange = TimeRange(),
    private val repeat: Repeat? = null
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "singlestat"
        embed(valueMappings)
        embed(timerange)
        if (repeat != null) {
            embed(repeat)
        }
    }
}