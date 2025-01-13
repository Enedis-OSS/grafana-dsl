/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.DashboardElement

@DashboardElement
interface PanelBuilder {

    var bounds: Pair<Int, Int>

    fun properties(propertiesSetter: (JSONObject) -> Unit)

    companion object {
        val DEFAULT_BOUNDS = 24 to 12
    }
}