/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.panels

/**
 * Text panel content mode
 * @author Aleksandr Korkin
 * @since 27.09.2019
 * */
enum class ContentMode(val value: String) {
    MARKDOWN("markdown"),
    HTML("html")
}