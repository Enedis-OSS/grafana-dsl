package fr.enedis.grafana.dsl.panels.imageIt

import org.json.JSONObject
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.stat.StatPanelDisplayOptionsBuilder

class ImageItPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator,
) : PanelBuilder {

    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS
    private var propertiesSetter: (JSONObject) -> Unit = {}
    var datasource: Datasource = Zabbix
    var transparent: Boolean = false
    var options: ImageItDisplayOptions = ImageItDisplayOptions()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: ImageItDisplayOptionsBuilder.() -> Unit) {
        val builder = ImageItDisplayOptionsBuilder()
        builder.build()
        options = builder.createImageItDisplayOptions()
    }


    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            ImageItPanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(emptyList())
                ),
                transparent = transparent,
                options = options,
            ),
            propertiesSetter
        )
    }
}


fun PanelContainerBuilder.imageItPanel(title: String, build: ImageItPanelBuilder.() -> Unit) {
    val builder = ImageItPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
