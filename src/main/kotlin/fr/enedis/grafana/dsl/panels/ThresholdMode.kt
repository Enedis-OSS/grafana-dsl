/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

/**
 * Thresholds mode
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
enum class ThresholdMode(val value: String) {
    /**
     * Thresholds are defined based on a number. For example, 80 on a scale of 1 to 150
     */
    ABSOLUTE("absolute"),

    /**
     * Thresholds are defined relative to minimum or maximum. For example, 80 percent
     */
    PERCENTAGE("percentage")
}