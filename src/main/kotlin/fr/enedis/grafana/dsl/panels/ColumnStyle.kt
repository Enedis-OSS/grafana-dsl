package fr.enedis.grafana.dsl.panels

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject

/**
 * The column styles allow you control how dates and numbers are formatted.
 *
 * @property unit Specify unit for numbers
 * @property decimals Specify decimals precision for numbers
 * @property pattern The Name or Regex field controls what columns the rule should be applied to.
 *  The regex or name filter will be matched against the column name not against column values.
 * @author Aleksandr Korkin
 * @since 12/12/19
 */
class ColumnStyle(
    private val unit: YAxis.Unit,
    private val decimals: Int? = null,
    private val pattern: String,
    private val alias: String? = null,
    private val type: ColumnStyleType? = null,
    private val hidden: Boolean = false
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "unit" to unit.unit
        "decimals" to decimals
        "type" to if(hidden) ColumnStyleType.HIDDEN else type?.type
        "pattern" to pattern
        "alias" to alias
    }
}
