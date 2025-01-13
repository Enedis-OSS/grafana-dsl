/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

import fr.enedis.grafana.dsl.datasource.Datasource

/**
 * Elastic datasource type
 */
interface ElasticDatasource : Datasource

object Elastic : ElasticDatasource {
    override fun asDatasourceName() = "Elastic"
}

/**
 * Create elastic datasource
 */
fun elasticDatasource(name: String): ElasticDatasource = SimpleElasticDatasource(name)

internal class SimpleElasticDatasource(private val name: String) : ElasticDatasource {
    override fun asDatasourceName(): String = name
}