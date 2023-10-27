package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.dashboard
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class MetricBuilderTest {

    @Test
    fun `should create zabbix text query`() {

        val expectedDashboard = dashboard("zabbix text query") {
            panels {
                singleStat("zabbix text query") {
                    metrics<Zabbix> {
                        textQuery {
                            host = "host.com"
                            application = "/App acquirer Ping General/"
                            item = "/service version/"
                            group = "/.*/"
                        }
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("MetricsBuilderTextQuery.json")
    }

    @Test
    fun `should create zabbix metrics query`() {

        val expectedDashboard = dashboard("zabbix metrics query") {
            panels {
                singleStat("zabbix metrics query") {
                    metrics<Zabbix> {
                        metricsQuery {
                            host = "host.com"
                            application = "/App acquirer Ping General/"
                            item = "/service version/"
                            group = "/.*/"
                        }
                    }
                }
            }
        }

        expectedDashboard.toString() shouldEqualToJson jsonFile("MetricsBuilderMetricsQuery.json")
    }
}