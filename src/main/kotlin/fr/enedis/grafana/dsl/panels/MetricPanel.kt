/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Graphite
import fr.enedis.grafana.dsl.json.emptyJsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.metrics.Metrics

/**
 * Panel with metrics.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class MetricPanel(
    private val basePanel: Panel,
    private val datasource: Datasource = Graphite,
    private val nullValue: NullValue = NullValue.NULL,
    private val metrics: Metrics,
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "datasource" to datasource.asDatasourceRefOrName()
        "nullPointMode" to nullValue.value
        "targets" to metrics
        "editable" to true
        "links" to emptyJsonArray()
    }
}

fun PanelContainerBuilder.metricPanel(title: String, build: MetricPanelBuilder.() -> Unit) {
    val builder = MetricPanelBuilder(title)
    builder.build()
    panels += builder.createPanel()
}