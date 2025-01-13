/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

/**
 * Generates metric id
 *
 * Current implementation generates only 'A'..'Z' sequence.
 * It should be enough for most graphs.
 *
 * @author Alexander Esipov
 * @since 03.12.2019
 */
internal class MetricIdGenerator {
    private var lastId: Char? = null

    /**
     * Return next metric id
     *
     * @throws IllegalStateException if last generated id was 'Z'
     */
    fun nextMetricId(): String {
        val nextId = lastId?.plus(1) ?: 'A'
        if (nextId > 'Z') {
            throw IllegalStateException("Current implementation supports only 'A'..'Z' auto generated metric ids")
        }
        lastId = nextId
        return nextId.toString()
    }
}