/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.datasource

object MetricsXyz : Datasource {
    override fun asDatasourceRef() = DatasourceRef("elasticsearch", "MetricsXyz")
}