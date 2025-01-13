/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.annotations

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.panels.Color
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

class ZabbixAnnotationTest {

    @Test
    fun `should create Zabbix annotation`() {
        val builder = AnnotationBuilder(name = "test")

        builder.enabled = false
        builder.hidden = true
        builder.color = Color.BLUE

        val annotation = builder.zabbix {
            group = "Zabbix"
            host = "/test/"
            application = "/app/"
            trigger = "/trigger/"

            minSeverity = ZabbixTriggerSeverity.DISASTER

            showOkEvents = true
            hideAcknowledgedEvents = true
            showHostName = true
        }

        annotation.toJson().toString() shouldEqualToJson jsonFile("ZabbixAnnotation.json")
    }

    @Test
    fun `should create minimal Zabbix annotation`() {
        val builder = AnnotationBuilder(name = "test")

        val annotation = builder.zabbix {}

        annotation.toJson().toString() shouldEqualToJson jsonFile("ZabbixAnnotationMinimal.json")
    }
}