package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray

class Thresholds(thresholds: Collection<Threshold>) : Json<JSONArray> by JsonArray(thresholds)
