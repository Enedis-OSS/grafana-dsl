package fr.enedis.grafana.dsl.annotations

import org.json.JSONArray
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.JsonArray

/**
 * A collection of annotations.
 */
class Annotations(annotations: List<Annotation>) : Json<JSONArray> by JsonArray(annotations)
