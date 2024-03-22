package fr.enedis.grafana.dsl.datasource


/**
 * Elastic datasource type
 */
interface GrafanaDatasource : Datasource

object Grafana : GrafanaDatasource {
    override fun asDatasourceName() = "grafana"
}

/**
 * Create grafana datasource
 */
fun grafanaDatasource(name: String): GrafanaDatasource = SimpleGrafanaDatasource(name)

internal class SimpleGrafanaDatasource(private val name: String) : GrafanaDatasource {
    override fun asDatasourceName(): String = name
}