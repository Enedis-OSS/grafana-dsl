package fr.enedis.grafana.dsl.panels.table

import fr.enedis.grafana.dsl.json.JsonArray
import fr.enedis.grafana.dsl.json.jsonObject
import fr.enedis.grafana.dsl.panels.ColumnStyle
import fr.enedis.grafana.dsl.panels.Panel
import fr.enedis.grafana.dsl.panels.repeat.Repeat
import fr.enedis.grafana.dsl.panels.transformation.PanelTransformation
import fr.enedis.grafana.dsl.time.Duration

/**
 * The table panel is very flexible, supporting both multiple modes for time series as well as for table,
 * annotation and raw JSON data.
 * It also provides date formatting and value formatting and coloring options.
 *
 * @author Aleksandr Korkin
 * @since 12/12/19
 */
class TablePanel(
    private val basePanel: Panel,
    private val timeFrom: Duration? = null,
    private val repeat: Repeat? = null,
    private val styles: List<ColumnStyle> = emptyList(),
    private val columns: List<TableColumn> = emptyList(),
    private val transform: TableTransform,
    private val transformations: List<PanelTransformation> = emptyList(),
    private val fieldConfig: TablePanelFieldConfig? = null
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "table"
        "timeFrom" to timeFrom?.toString()
        "columns" to JsonArray(columns)
        "fontSize" to "100%"
        "styles" to JsonArray(styles)
        "transform" to transform.transform
        "fieldConfig" to fieldConfig
        "transformations" to JsonArray(transformations)
        if (repeat != null) {
            embed(repeat)
        }
    }
}
