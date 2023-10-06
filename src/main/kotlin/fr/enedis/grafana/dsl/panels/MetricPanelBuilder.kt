package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Graphite
import fr.enedis.grafana.dsl.datasource.GraphiteDatasource
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.generators.SimplePanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.DashboardMetric
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.metrics.MetricsBuilder

class MetricPanelBuilder(private val title: String) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private val metrics = mutableListOf<DashboardMetric>()

    private val panelLayoutGenerator: PanelLayoutGenerator = SimplePanelLayoutGenerator()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    @Deprecated(message = "pass datasource as the first function argument explicitly")
    fun metrics(build: MetricsBuilder<out GraphiteDatasource>.() -> Unit) {
        val builder = MetricsBuilder<GraphiteDatasource>()
        builder.build()
        metrics += builder.metrics
    }

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics += builder.metrics
        this.datasource = datasource
    }

    internal fun createPanel(): Panel = AdditionalPropertiesPanel(
            MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
            ),
            propertiesSetter
    )
}
