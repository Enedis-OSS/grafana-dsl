/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.dashboard.link

/**
 * Type of dashboard's link icon
 */
enum class DashboardLinkIconShape(val value: String) {

    /** Icon indicates external link */
    EXTERNAL_LINK("external link"),

    /** Icon indicates link to dashboard */
    DASHBOARD("dashboard"),

    /** Question mark icon */
    QUESTION("question"),

    /** info icon, like 🛈 */
    INFO("info"),

    /** bolt icon ,like 🗲 */
    BOLT("bolt"),

    /** doc icon, like 🗎 */
    DOC("doc"),

    /** cloud icon, like ☁ */
    CLOUD("cloud")
}