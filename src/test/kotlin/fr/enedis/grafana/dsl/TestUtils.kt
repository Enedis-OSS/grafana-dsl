/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl

import org.apache.commons.io.IOUtils
import org.skyscreamer.jsonassert.JSONAssert

infix fun String.shouldEqualToJson(json: String) {
    JSONAssert.assertEquals(json, this, true)
}

fun Any.jsonFile(fileName: String): String {
    return IOUtils.toString(this::class.java.getResourceAsStream(fileName), "UTF-8")
}