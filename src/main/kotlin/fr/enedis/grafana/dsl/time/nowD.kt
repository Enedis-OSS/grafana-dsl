/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.time

/**
 * Start of a day
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
object nowD : Timestamp {

    override fun toJson() = "now/d"

    override fun toString() = "now/d"

    operator fun minus(duration: Duration) = object : Timestamp {

        override fun toJson() = "now/d-$duration"

        override fun toString() = "now/d-$duration"
    }

    operator fun plus(duration: Duration) = object : Timestamp {

        override fun toJson() = "now/d+$duration"

        override fun toString() = "now/d+$duration"
    }
}