package fr.enedis.grafana.dsl.panels.timeSeries

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.Timerange
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation

class TimeSeriesPanel(private val basePanel: Panel,
                      private val timerange: Timerange = Timerange(),
                      private val repeat: Repeat? = null,
                      private val fieldConfig: TimeSeriesPanelFieldConfig = TimeSeriesPanelFieldConfig(),
                      private val options: TimeSeriesPanelDisplayOptions = TimeSeriesPanelDisplayOptions(),
                      private val transformations: List<PanelTransformation> = emptyList(),
) : Panel {


    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "timeseries"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
        "transformations" to JsonArray(transformations)
    }

}