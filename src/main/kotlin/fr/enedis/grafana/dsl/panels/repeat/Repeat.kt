/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.repeat

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.variables.Variable

/**
 * Repeat tab in panel that can apply panel for all values from variable
 * @author Aleksey Antufev
 * @since 19.09.2019
 */
class Repeat(private val variable: Variable, private val direction: Direction = Horizontal()) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "repeat" to variable.name
        embed(direction)
    }
}