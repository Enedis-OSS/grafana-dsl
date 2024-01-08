package fr.enedis.grafana.dsl.panels.barChart

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.TimeRange
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation

class BarChartPanel(private val basePanel: Panel,
                    private val timerange: TimeRange = TimeRange(),
                    private val repeat: Repeat? = null,
                    private val fieldConfig: BarChartPanelFieldConfig = BarChartPanelFieldConfig(),
                    private val options: BarChartPanelDisplayOptions = BarChartPanelDisplayOptions(),
                    private val transformations: List<PanelTransformation> = emptyList(),
) : Panel {


    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "barchart"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
        "transformations" to JsonArray(transformations)
    }

}