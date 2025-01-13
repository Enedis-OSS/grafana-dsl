/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.stat

/**
 * Graph display mode
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
enum class GraphMode(val value: String) {
    /**
     * Hides the graph and only shows the value
     */
    NONE("none"),

    /**
     * Shows the area graph below the value. This requires that your query returns a time column
     */
    AREA("area");
}