package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONObject
import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.metrics.ReferencedDashboardMetric
import fr.enedis.grafana.dsl.metrics.functions.StringMetric
import fr.enedis.grafana.dsl.shouldEqualToJson
import fr.enedis.grafana.dsl.time.m

class AlertBuilderTest {

    @Test
    fun `should create alerting panel`() {
        val builder = AlertBuilder("Alert name").apply {
            frequency = 5.m
            pendingFor = 10.m
            onNoData = Alerting
            message = "Alert message"
            notificationUids += "ABC"
            notificationUids += "DEF"

            conditions {
                query(
                    ReferencedDashboardMetric(
                        StringMetric("*"),
                        "A",
                        false
                    ),
                    15.m
                ).isAbove(3000)
            }

            thresholds {
                threshold { ThresholdDsl.gt(3000) }
            }
        }

        val alertingPanelJson = JSONObject()
            .apply {  builder.createAlertingPanel().invoke(this) }
            .toString()

        alertingPanelJson shouldEqualToJson jsonFile("AlertingPanel.json")
    }
}