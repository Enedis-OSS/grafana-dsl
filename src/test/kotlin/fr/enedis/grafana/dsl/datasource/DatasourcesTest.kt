/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.datasource

import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DatasourcesTest {

    @ParameterizedTest
    @MethodSource("representations")
    fun `should serialize data sources correctly`(dataSource: Datasource, representation: String?) {
        dataSource.asDatasourceName() shouldBeEqualTo representation
    }

    @Test
    fun `should serialize data sources ref correctly`() {
        MetricsXyz.asDatasourceRef().toJson().toString() shouldEqualToJson jsonObject {
            "type" to "elasticsearch"
            "uid" to "MetricsXyz"
        }.toString()
    }

    companion object {
        @JvmStatic
        fun representations() = arrayOf(
            arrayOf(Zabbix, "Zabbix"),
            arrayOf(Graphite, "Graphite")
        )
    }
}