package fr.enedis.grafana.dsl.panels.repeat

import fr.enedis.grafana.dsl.json.jsonObject

/**
 * Vertical direction singlestat group panels
 *
 * @author Aleksey Antufev
 * @since 19.09.2019
 */
class Vertical : Direction {

    override fun toJson() = jsonObject {
        "repeatDirection" to "v"
    }
}
