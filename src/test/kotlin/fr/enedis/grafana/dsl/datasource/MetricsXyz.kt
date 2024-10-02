package fr.enedis.grafana.dsl.datasource

object MetricsXyz : Datasource {
    override fun asDatasourceRef() = DatasourceRef("elasticsearch", "MetricsXyz")
}
