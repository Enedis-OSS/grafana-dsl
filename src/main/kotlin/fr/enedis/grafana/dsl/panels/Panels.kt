package fr.enedis.grafana.dsl.panels

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray

/**
 * Panels of a dashboard.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Panels(private val panels: List<Panel>) : Json<JSONArray> by JsonArray(panels)
