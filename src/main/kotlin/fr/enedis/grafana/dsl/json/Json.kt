/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.json

/**
 * Represents an object that can be serialized to JSON.
 *
 * @param J Type of JSON object to be serialized to (string, array, object, etc)
 *
 * @author Dmitry Komarov
 * @since 24.07.2018
 */
interface Json<out J> {

    /**
     * returns object as JSON.
     *
     * @return JSON
     */
    fun toJson(): J
}