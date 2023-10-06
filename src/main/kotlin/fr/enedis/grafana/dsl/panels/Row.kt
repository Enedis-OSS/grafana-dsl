package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Row on a dashboard.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Row(
    private val basePanel: Panel,
    private val repeat: String?,
    private val collapsed: Boolean? = false
) : Panel {
    var panels: Panels = Panels(mutableListOf())

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "row"
        "repeat" to repeat
        "collapsed" to collapsed
        "panels" to panels
    }
}
