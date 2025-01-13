/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json

/**
 * Represents "aliasColors" block on a panel
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
class AliasColors : Json<JSONObject> {

    private val aliasToColor: MutableMap<String, String> = mutableMapOf()

    operator fun set(key: String, value: Color) {
        aliasToColor[key] = value.asHexString()
    }

    override fun toJson() = JSONObject(aliasToColor)
}