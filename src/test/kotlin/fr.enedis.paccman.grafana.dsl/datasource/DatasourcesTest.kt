package fr.enedis.grafana.dsl.datasource

import org.amshove.kluent.shouldEqual
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DatasourcesTest {

    @ParameterizedTest
    @MethodSource("representations")
    fun `should serialize data sources correctly`(dataSource: Datasource, representation: String?) {
        dataSource.asDatasourceName() shouldEqual representation
    }

    companion object {
        @JvmStatic
        fun representations() = arrayOf(
            arrayOf(Zabbix, "Zabbix"),
            arrayOf(Graphite, "Graphite")
        )
    }
}
