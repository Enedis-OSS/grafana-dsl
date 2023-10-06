package fr.enedis.grafana.dsl.panels.alerting

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json

interface AlertingCondition : Json<JSONObject>
