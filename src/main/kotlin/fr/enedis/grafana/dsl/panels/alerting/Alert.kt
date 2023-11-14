package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.set
import fr.enedis.grafana.dsl.time.Duration
import fr.enedis.grafana.dsl.time.m

class Alert(
    private val name: String,
    private val message: String,
    private val handler: Int,
    private val frequency: Duration = 1.m,
    private val onNoData: AlertingState = Ok,
    private val onExecutionError: AlertingState = Alerting,
    private val notificationUids: List<String> = emptyList(),
    private val notificationIds: List<Long> = emptyList(),
    private val conditions: AlertingConditions,
    private val alertRuleTags: MutableMap<String, String> = mutableMapOf(),
    private val pendingFor: Duration = 0.m
) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["name"] = name
        json["message"] = message
        json["handler"] = handler
        json["frequency"] = frequency.toString()
        json["noDataState"] = onNoData.asState()
        json["executionErrorState"] = onExecutionError.asState()
        json["notifications"] = JSONArray(
            notificationIds.map { JSONObject().put("id", it) } +
                    notificationUids.map { JSONObject().put("uid", it) }
        )
        json["conditions"] = conditions.toJson()
        json["for"] = pendingFor.toString()
        json["alertRuleTags"] = JSONObject(alertRuleTags)

        return json
    }
}
