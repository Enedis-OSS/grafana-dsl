package fr.enedis.grafana.dsl.panels.pieChart

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.Timerange
import fr.enedis.grafana.dsl.panels.repeat.Repeat

class PieChartPanel(
    private val basePanel: Panel,
    private val timerange: Timerange = Timerange(),
    private val repeat: Repeat? = null,
    private val fieldConfig: PieChartPanelFieldConfig = PieChartPanelFieldConfig(),
    private val options: PieChartPanelDisplayOptions = PieChartPanelDisplayOptions(),
) : Panel {


    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "piechart"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
    }

}