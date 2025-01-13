/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.generators

import fr.enedis.grafana.dsl.panels.Position

/**
 * Generates id and position for panels and rows.
 * @author Aleksey Antufev
 * @since 27.09.2019
 */
interface PanelLayoutGenerator {
    fun nextId(): Long

    fun nextPosition(width: Int = 0, height: Int = 9): Position

    fun resetXAxis()
}