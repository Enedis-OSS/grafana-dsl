/*
 * SPDX-FileCopyrightText: 2023-2025 Enedis
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.imageIt

import org.json.JSONObject
import fr.enedis.grafana.dsl.datasource.Datasource
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.generators.PanelLayoutGenerator
import fr.enedis.grafana.dsl.metrics.Metrics
import fr.enedis.grafana.dsl.panels.*
import fr.enedis.grafana.dsl.panels.stat.StatPanelDisplayOptionsBuilder

class ImageItPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator,
) : PanelBuilder {

    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS
    private var propertiesSetter: (JSONObject) -> Unit = {}
    var datasource: Datasource = Zabbix
    var transparent: Boolean = false
    var options: ImageItDisplayOptions = ImageItDisplayOptions()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: ImageItDisplayOptionsBuilder.() -> Unit) {
        val builder = ImageItDisplayOptionsBuilder()
        builder.build()
        options = builder.createImageItDisplayOptions()
    }


    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            ImageItPanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(emptyList())
                ),
                transparent = transparent,
                options = options,
            ),
            propertiesSetter
        )
    }
}


/**
 * The support of pierosavi-imageit-panel plugin will stop at Grafana 9.0, you can use the Text Panel instead.
 * You can replace the imageItPanel() function with the textPanel() function like this:
 * ```kotlin
 *  imageItPanel("My Image") {
 *      bounds = 3 to 6
 *      transparent = true
 *      datasource = Zabbix
 *      options {
 *          imageUrl = "https://placehold.it/100x100&text=ImageIt+Panel"
 *      }
 *  }
 *  // can be replaced with
 *  textPanel("My Image") {
 *       bounds = 3 to 6
 *       transparent = true
 *       mode = ContentMode.HTML
 *       content = """
 *           <center>
 *               <img style="height:${6 * 30}px;" src="https://placehold.it/100x100&text=ImageIt+Panel">
 *           </center>
 *       """.trimIndent()
 *  }
 *  // you can adapt the usage meets your needs
 *
 * ```
 * @see textPanel
 */
@Deprecated("The support will stop at Grafana 9.0, you can use the Text Panel instead")
fun PanelContainerBuilder.imageItPanel(title: String, build: ImageItPanelBuilder.() -> Unit) {
    val builder = ImageItPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}