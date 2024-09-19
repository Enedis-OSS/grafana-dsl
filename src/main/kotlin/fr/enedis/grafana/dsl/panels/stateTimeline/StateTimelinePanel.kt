package fr.enedis.grafana.dsl.panels.stateTimeline

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.TimeRange
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation

/**
 * A state timeline visualization displays data in a way that shows state changes over time.
 * https://grafana.com/docs/grafana/latest/panels/visualizations/state-timeline/
 * @author Anis JOLLY-LAARIF
 * @since 12.09.2024
 */
class StateTimelinePanel(
    private val basePanel: Panel,
    private val timerange: TimeRange = TimeRange(),
    private val repeat: Repeat? = null,
    private val fieldConfig: StateTimelineFieldConfig = StateTimelineFieldConfig(),
    private val options: StateTimelineDisplayOptions = StateTimelineDisplayOptions(),
    private val transformations: List<PanelTransformation> = emptyList()
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "state-timeline"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
        "transformations" to JsonArray(transformations)
    }
}
