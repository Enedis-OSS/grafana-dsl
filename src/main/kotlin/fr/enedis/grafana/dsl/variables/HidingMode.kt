/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.variables

/**
 * Specifies how variable will be hided.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
enum class HidingMode(val mode: Int) {

    /**
     * Both label and value of variable will be shown at dashboard screen.
     */
    NONE(0),

    /**
     * Only value of variable will be shown at dashboard screen.
     */
    LABEL(1),

    /**
     * Both label and value of variable will be hidden from dashboard screen.
     */
    VARIABLE(2)
}