/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels.stat

/**
 * Alignment mode
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
enum class JustifyMode(val value: String) {
    /**
     * If only a single value is shown (no repeat), then the value is centered.
     * If multiple series or rows are shown, then the value is left-aligned
     */
    AUTO("auto"),

    /**
     * Stat value is centered
     */
    CENTER("center");
}