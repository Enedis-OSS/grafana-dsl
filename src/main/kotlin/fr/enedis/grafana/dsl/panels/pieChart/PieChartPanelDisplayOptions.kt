package fr.enedis.grafana.dsl.panels.pieChart


import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.Orientation

enum class PieType(val value: String) {
    DONUT("donut"), PIE("pie")
}

/**
 * Options to refine visualization
 * @since 02.10.2022
 */
class PieChartPanelDisplayOptions(
    private val reduceOptions: PieChartPanelReduceOptions = PieChartPanelReduceOptions(),
    private val pieType: PieType = PieType.PIE,
    private val displayLabels: List<String> = emptyList<String>(),
    private val legend: LegendPieChart = LegendPieChart()
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
        "pieType" to pieType.value
        "displayLabels" to displayLabels
        "legend" to legend
    }
}

/**
 * Builder for PieChartPanelDisplayOptions
 * @since 02.10.2022
 */
class PieChartPanelDisplayOptionsBuilder {
    private var reduceOptions: PieChartPanelReduceOptions = PieChartPanelReduceOptions()
    var pieType: PieType = PieType.PIE
    var orientation: Orientation = Orientation.AUTO
    var displayLabels: List<String> = emptyList<String>()
    var legend: LegendPieChart = LegendPieChart()

    fun reduceOptions(build: GaugePanelReduceOptionsBuilder.() -> Unit) {
        val builder = GaugePanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun legend(build: LegendPieChartBuilder.() -> Unit) {
        val builder = LegendPieChartBuilder()
        builder.build()
        legend = builder.createLegend()
    }

    fun createPieChartPanelDisplayOptions() = PieChartPanelDisplayOptions(
        reduceOptions = reduceOptions,
        pieType = pieType,
        displayLabels = displayLabels,
        legend = legend
    )
}

/**
 * @since 02.10.2022
 */
class PieChartPanelReduceOptions(
    private val values: Boolean = false,
    private val calcs: List<String> = listOf("lastNotNull"),
    private val fields: String = "",
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to values
        "calcs" to calcs
        "fields" to fields
    }
}

class GaugePanelReduceOptionsBuilder {
    var fields: String = ""
    var values: Boolean = false
    var calcs: List<String> = listOf("lastNotNull")
    fun createReduceOptions(): PieChartPanelReduceOptions {
        return PieChartPanelReduceOptions(fields = fields, values = values, calcs = calcs)
    }
}

