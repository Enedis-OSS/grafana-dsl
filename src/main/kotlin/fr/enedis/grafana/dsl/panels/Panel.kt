package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json

/**
 * Panel on dashboard.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
interface Panel : Json<JSONObject>
