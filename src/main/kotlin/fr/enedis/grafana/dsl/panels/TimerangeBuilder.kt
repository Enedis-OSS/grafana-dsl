/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.time.Duration

/**
 * Builder for Timerange.
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
class TimerangeBuilder {

    var lastTime: String? = null
    var timeShift: String? = null
    var hideTimeOverrideInfo: Boolean = false

    fun createTimerange(): TimeRange {
        return TimeRange(
            lastTime = lastTime,
            timeShift = timeShift,
            hideTimeOverrideInfo = hideTimeOverrideInfo
        )
    }
}