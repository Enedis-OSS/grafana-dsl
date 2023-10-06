package fr.enedis.grafana.dsl.panels.gauge

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.Timerange
import fr.enedis.grafana.dsl.panels.repeat.Repeat

/**
 * Gauge panel presents text from defined metric
 * https://grafana.com/docs/grafana/latest/panels/visualizations/gauge-panel/
 * @author Aleksey Matveev
 * @since 29.10.2020
 */
class GaugePanel(
    private val basePanel: Panel,
    private val timerange: Timerange = Timerange(),
    private val repeat: Repeat? = null,
    private val fieldConfig: GaugePanelFieldConfig = GaugePanelFieldConfig(),
    private val options: GaugePanelDisplayOptions = GaugePanelDisplayOptions(),
    private val transformations: List<GaugePanelTransformation> = emptyList(),
  ) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "gauge"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
        "transformations" to JsonArray(transformations)
    }
}
