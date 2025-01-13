/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.imageIt

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel

class ImageItPanel(
    private val basePanel: Panel,
    private val transparent: Boolean = false,
    private val options: ImageItDisplayOptions = ImageItDisplayOptions(),
) : Panel {

    override fun toJson(): JSONObject = jsonObject(basePanel.toJson()) {
        "type" to "pierosavi-imageit-panel"
        "transparent" to transparent
        "options" to options
    }
}

val j = """
    {
      "type": "pierosavi-imageit-panel",
      "title": "Panel Title",
      "gridPos": {
        "x": 0,
        "y": 0,
        "w": 12,
        "h": 8
      },
      "id": 23763571993,
      "fieldConfig": {
        "defaults": {},
        "overrides": []
      },
      "pluginVersion": "1.0.6",
      "targets": [
        {
          "refId": "A",
          "queryType": "randomWalk"
        }
      ],
      "timeFrom": null,
      "timeShift": null,
      "options": {
        "imageUrl": "https://i.ibb.co/tLXrjb6/imageit.png",
        "forceImageRefresh": false,
        "lockSensors": false,
        "sensorsTextSize": 10,
        "sensors": [],
        "mappings": []
      },
      "datasource": null
    }
""".trimIndent()