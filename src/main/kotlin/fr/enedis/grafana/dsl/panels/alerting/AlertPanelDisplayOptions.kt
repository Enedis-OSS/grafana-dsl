package fr.enedis.grafana.dsl.panels.alerting

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.alerting.ShowOptions.CURRENT
import fr.enedis.grafana.dsl.panels.alerting.SortOrder.TIME_DESC
import org.json.JSONObject

class AlertPanelDisplayOptions(
    private val showOptions: ShowOptions = CURRENT,
    private val maxItems: Int = 10,
    private val sortOrder: SortOrder = TIME_DESC,
    private val dashboardAlerts: Boolean = false,
    private val alertNameFilter: String = "",
    private val dashboardTitleFilter: String = "",
    private val folderId: Long? = null,
    private val stateFilter: AlertPanelStateFilter = AlertPanelStateFilter()

) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "showOptions" to showOptions.value
        "maxItems" to maxItems
        "sortOrder" to sortOrder.value
        "dashboardAlerts" to dashboardAlerts
        "alertName" to alertNameFilter
        "folderId" to folderId
        "dashboardTitle" to dashboardTitleFilter
        "stateFilter" to jsonObject {
            "ok" to stateFilter.ok
            "paused" to stateFilter.paused
            "no_data" to stateFilter.no_data
            "execution_error" to stateFilter.execution_error
            "alerting" to stateFilter.alerting
            "pending" to stateFilter.pending
        }
    }
}

enum class ShowOptions(val value: String) {
    CURRENT("current"),
    RECENT_CHANGES("changes")
}

enum class SortOrder(val value: Int) {
    ALPHA_ASC(1),
    ALPHA_DESC(2),
    IMPORTANCE(3),
    TIME_ASC(4),
    TIME_DESC(5),
}

class AlertPanelStateFilter(
    val ok: Boolean = false,
    val paused: Boolean = false,
    val no_data: Boolean = false,
    val execution_error: Boolean = false,
    val alerting: Boolean = false,
    val pending: Boolean = false,
)