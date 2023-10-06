package fr.enedis.grafana.dsl.panels.graph.display.seriesoverrides

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Series specific overrides on graph panel
 *
 * @author Aleksey Antufev
 * @since 04.09.2019
 */
class SeriesOverride(
    private val alias: String,
    private var bars: Boolean = false,
    private var lines: Boolean = false,
    private var stack: Boolean = false,
    private var lineWidth: Int = 0
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "alias" to alias
        "bars" to bars
        "lines" to lines
        "linewidth" to lineWidth
        "stack" to stack
    }
}
