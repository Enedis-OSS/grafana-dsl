package fr.enedis.grafana.dsl.annotations

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json

/**
 * An annotation that displayed on graphs.
 *
 * Annotations can be used to mark some important events, e.g. new version release or start of marketing company.
 */
interface Annotation : Json<JSONObject>
