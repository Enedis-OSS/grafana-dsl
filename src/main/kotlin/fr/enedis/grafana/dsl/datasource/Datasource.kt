package fr.enedis.grafana.dsl.datasource

/**
 * Data source for metrics.
 *
 * @author Dmitry Komarov
 * @since 7/23/18
 */
interface Datasource {

    fun asDatasourceRef(): DatasourceRef? = null

    /**
     * Returns string representation of this data source.
     *
     * @return title
     *
     * Old way of referencing a datasource by its name, replaced in Grafana v8.4.3 by Type and UID referencing.
     */
    @Deprecated("Initialise asDataSourceRef instead", ReplaceWith("datasourceRef().toJson()"))
    fun asDatasourceName(): String? = null


    /**
     * Returns data source reference or name.
     *
     * @return data source reference or name
     * @throws IllegalStateException if data source has neither reference nor name
     *
     * Tobe removed and replaced with datasourceRef().toJson() when asDatasourceName() is removed
     */
    fun asDatasourceRefOrName(): Any =
        asDatasourceRef()?.toJson() ?: asDatasourceName() ?: error("Datasource should have either name or ref")
}
