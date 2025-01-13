/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray

/**
 * Variables, used in metric queries.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Variables(private val variables: List<Variable>) : Json<JSONArray> by JsonArray(variables)