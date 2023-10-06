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
    private val reduceOptions: StatPanelReduceOptions = StatPanelReduceOptions(),
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "orientation" to orientation.value
        "textMode" to textMode.value
        "colorMode" to colorMode.value
        "graphMode" to graphMode.value
        "justifyMode" to justifyMode.value
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
    private var reduceOptions: StatPanelReduceOptions = StatPanelReduceOptions()
    fun reduceOptions(build: StatPanelReduceOptionsBuilder.() -> Unit) {
        val builder = StatPanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun createStatPanelDisplayOptions() = StatPanelDisplayOptions(
        colorMode = colorMode,
        graphMode = graphMode,
        textMode = textMode,
        orientation = orientation,
        justifyMode = justifyMode,
        reduceOptions = reduceOptions
    )
}

/**
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelReduceOptions(
    private val fields: String = "",
    private val calcs: List<String> = listOf("lastNotNull"),
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to false
        "calcs" to calcs
        "fields" to fields
    }
}

class StatPanelReduceOptionsBuilder() {
    var fields: String = ""
    var calcs: List<String> = listOf("lastNotNull")
    fun createReduceOptions(): StatPanelReduceOptions {
        return StatPanelReduceOptions(fields = fields, calcs = calcs)
    }
}

