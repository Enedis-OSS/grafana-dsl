/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json

/**
 * Variable in Grafana.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
interface Variable : Json<JSONObject> {

    /**
     * Variable name.
     */
    val name: String

    /**
     * Returns string representation of this variable.
     */
    fun asVariable(): String = "$$name"
}