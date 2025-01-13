/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

/**
 * A stacking direction
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
enum class Orientation(val value: String) {
    /**
     * Grafana selects what it thinks is the best orientation
     */
    AUTO("auto"),

    /**
     * Bars stretch horizontally, left to right
     */
    HORIZONTAL("horizontal"),

    /**
     * Bars stretch vertically, top to bottom
     */
    VERTICAL("vertical");
}