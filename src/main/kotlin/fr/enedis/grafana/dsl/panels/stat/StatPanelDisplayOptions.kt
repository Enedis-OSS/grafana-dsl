/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.stat

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation

/**
 * Options to refine visualization
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelDisplayOptions(
    private val colorMode: ColorMode = ColorMode.VALUE,
    private val orientation: Orientation = Orientation.HORIZONTAL,
    private val textMode: TextMode = TextMode.AUTO,
    private val graphMode: GraphMode = GraphMode.NONE,
    private val justifyMode: JustifyMode = JustifyMode.AUTO,
    private val text: StatPanelText? = null,
    private val reduceOptions: StatPanelReduceOptions = StatPanelReduceOptions(),
    private val wideLayout: Boolean = true,

    ) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "orientation" to orientation.value
        "textMode" to textMode.value
        "colorMode" to colorMode.value
        "graphMode" to graphMode.value
        "justifyMode" to justifyMode.value
        "text" to text
        "wideLayout" to wideLayout
    }
}

/**
 * Builder for StatPanelDisplayOptions
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelDisplayOptionsBuilder {
    var colorMode: ColorMode = ColorMode.VALUE
    var orientation: Orientation = Orientation.HORIZONTAL
    var textMode: TextMode = TextMode.AUTO
    var graphMode: GraphMode = GraphMode.NONE
    var justifyMode: JustifyMode = JustifyMode.AUTO
    var text: StatPanelText? = null
    var wideLayout: Boolean = true
    private var reduceOptions: StatPanelReduceOptions = StatPanelReduceOptions()
    fun reduceOptions(build: StatPanelReduceOptionsBuilder.() -> Unit) {
        val builder = StatPanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun text(build: StatPanelTextBuilder.() -> Unit) {
        val builder = StatPanelTextBuilder()
        builder.build()
        text = builder.createText()
    }

    fun createStatPanelDisplayOptions() = StatPanelDisplayOptions(
        colorMode = colorMode,
        graphMode = graphMode,
        textMode = textMode,
        orientation = orientation,
        justifyMode = justifyMode,
        text = text,
        reduceOptions = reduceOptions,
        wideLayout = wideLayout
    )
}

/**
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelReduceOptions(
    private val fields: String = "",
    private val calcs: List<String> = listOf("lastNotNull"),
    private val values: Boolean = false
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to values
        "calcs" to calcs
        "fields" to fields
    }
}

class StatPanelReduceOptionsBuilder() {
    var fields: String = ""
    var calcs: List<String> = listOf("lastNotNull")
    var values: Boolean = false
    fun createReduceOptions(): StatPanelReduceOptions {
        return StatPanelReduceOptions(fields = fields, calcs = calcs, values = values)
    }
}

class StatPanelText(
    private val valueSize: Int = 0,
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "valueSize" to valueSize
    }
}

class StatPanelTextBuilder() {
    var valueSize: Int = 0
    fun createText(): StatPanelText {
        return StatPanelText(valueSize = valueSize)
    }
}
