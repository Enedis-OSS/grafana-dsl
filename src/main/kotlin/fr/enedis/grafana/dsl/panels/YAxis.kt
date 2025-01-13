/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.json.Json
import fr.enedis.grafana.dsl.json.jsonObject
import org.json.JSONObject

/**
 * Describes block "Left Y" or "Right Y" for Y-axis panel in Legend block
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
class YAxis(
    private val label: String? = null,
    private val unit: DataUnit = DataUnit.SHORT,
    private val scale: Scale = Scale.LINEAR,
    private val show: Boolean = true,
    private val decimals: Int? = null,
    private val min: Int? = null,
    private val max: Int? = null
) : Json<JSONObject> {

    enum class Scale(val logBase: Int) {
        LINEAR(1),
        LOG2(2),
        LOG10(10),
        LOG32(32),
        LOG1024(1024)
    }

    override fun toJson() = jsonObject {
        "format" to unit.unit
        "label" to label
        "logBase" to scale.logBase
        "show" to show
        "decimals" to decimals
        "min" to min
        "max" to max
    }
}