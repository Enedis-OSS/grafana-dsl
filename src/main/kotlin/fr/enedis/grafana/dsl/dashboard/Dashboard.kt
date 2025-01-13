/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard

import fr.enedis.grafana.dsl.annotations.Annotations
import fr.enedis.grafana.dsl.dashboard.link.DashboardLinks
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Panels
import fr.enedis.grafana.dsl.time.*
import fr.enedis.grafana.dsl.variables.Variables
import org.json.JSONObject

/**
 *
 * Dashboard. Represents root of JSON-document, used for importing into Grafana.
 *
 * @property title Display title
 * @property timeRange Metrics time range
 * @property refresh Metrics refresh rate
 * @property tags Tags
 * @property variables Variables that should be reused for querying metrics
 * @property panels Panels
 * @property editable Ability to edit the dashboard
 * @property annotations Annotations that displayed on graphs. Has default value for backward compatibility.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Dashboard(
    val uid: String? = null,
    val title: String,
    val timeRange: TimeRange,
    val refresh: Refresh,
    val nowDelay: Duration? = null,
    val tags: Tags,
    val variables: Variables,
    val panels: Panels,
    val editable: Boolean,
    val annotations: Annotations = Annotations(emptyList()),
    val links: DashboardLinks = DashboardLinks(emptyList()),
    val graphTooltip: GraphTooltip = GraphTooltip.DEFAULT,
) : Json<JSONObject> {

    init {
        if (uid != null && uid.length > 40) {
            throw IllegalArgumentException("uid must be between 0 and 40")
        }
    }

    override fun toJson() = jsonObject {
        "uid" to uid
        "title" to title
        "time" to timeRange
        "refresh" to refresh.asRefreshPeriod()
        "tags" to tags
        "panels" to panels
        "templating" to jsonObject {
            "list" to variables
        }
        "annotations" to jsonObject {
            "list" to annotations
        }
        "editable" to editable
        "graphTooltip" to graphTooltip.value
        "links" to links
        "schemaVersion" to 39
        "style" to "dark"
        "timepicker" to jsonObject {
            "nowDelay" to nowDelay?.toString()
            "refresh_intervals" to jsonArray[5.s, 10.s, 30.s, 1.m, 5.m, 15.m, 30.m, 1.h, 2.h, 1.d]
            "time_options" to jsonArray[5.m, 15.m, 1.h, 6.h, 12.h, 24.h, 2.d, 7.d, 30.d]
        }
        "timezone" to "browser"
    }

    override fun toString(): String {
        return toJson().toString(4)
    }
}