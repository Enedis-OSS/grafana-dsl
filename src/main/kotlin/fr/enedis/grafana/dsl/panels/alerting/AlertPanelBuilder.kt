package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.panels.*
import org.json.JSONObject

class AlertPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator,
) : PanelBuilder {
    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS

    private var propertiesSetter: (JSONObject) -> Unit = {}
    var datasource: Datasource = Zabbix
    var options: AlertPanelDisplayOptions = AlertPanelDisplayOptions()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: AlertDisplayOptionsBuilder.() -> Unit) {
        val builder = AlertDisplayOptionsBuilder()
        builder.build()
        options = builder.createAlertDisplayOptions()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            AlertPanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(emptyList())
                ),
                options = options,
            ),
            propertiesSetter
        )
    }
}

fun PanelContainerBuilder.alertPanel(title: String, build: AlertPanelBuilder.() -> Unit) {
    val builder = AlertPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}