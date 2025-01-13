/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.imageIt

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject


class ImageItDisplayOptions(
    private val imageUrl: String = "https://i.ibb.co/tLXrjb6/imageit.png",
    private val forceImageRefresh: Boolean = false,
    private val lockSensors: Boolean = false,
    private val sensorsTextSize: Int = 10,
    private val sensors: List<String> = emptyList(),
    private val mappings: List<String> = emptyList(),
) : Json<JSONObject> {

    override fun toJson(): JSONObject = jsonObject {
        "imageUrl" to imageUrl
        "forceImageRefresh" to forceImageRefresh
        "lockSensors" to lockSensors
        "sensorsTextSize" to sensorsTextSize
        "sensors" to sensors
        "mappings" to mappings
    }


}