package fr.enedis.grafana.dsl.panels.barGauge

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.TimeRange
import fr.enedis.grafana.dsl.panels.repeat.Repeat

class BarGaugePanel(
    private val basePanel: Panel,
    private val timerange: TimeRange = TimeRange(),
    private val repeat: Repeat? = null,
    private val fieldConfig: BarGaugePanelFieldConfig = BarGaugePanelFieldConfig(),
    private val options: BarGaugePanelDisplayOptions = BarGaugePanelDisplayOptions()
) : Panel {


    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "bargauge"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
    }

}

