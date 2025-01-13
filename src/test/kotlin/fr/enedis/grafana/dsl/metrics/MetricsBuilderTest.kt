/*
 * SPDX-FileCopyrightText: 2021-2022 NBCO YooMoney LLC
 *
 * SPDX-License-Identifier: MIT
 *
 */

package fr.enedis.grafana.dsl.metrics

import fr.enedis.grafana.dsl.datasource.Graphite
import fr.enedis.grafana.dsl.datasource.PromQl
import fr.enedis.grafana.dsl.metrics.functions.StringMetric
import fr.enedis.grafana.dsl.metrics.prometheus.asInstantVector
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class MetricsBuilderTest {

    @Test
    fun `metric with default referenceId`() {
        // given
        val metricsBuilder = MetricsBuilder<Graphite>()

        // when
        metricsBuilder.metric(referenceId = "A") {
            StringMetric("*")
        }
        metricsBuilder.metric(referenceId = "Z") {
            StringMetric("*")
        }
        (1..24).forEach {
            metricsBuilder.metric {
                StringMetric("*")
            }
        }

        // then
        metricsBuilder.metrics.map { it.id } shouldContainAll ('A'..'Z').map { it.toString() }
    }

    //@Test(expectedExceptions = [IllegalStateException::class], expectedExceptionsMessageRegExp = "Current implementation supports only.*")
    @Test
    fun `metric with default referenceId limits`() {
        val illegalStateException = Assertions.assertThrows(IllegalStateException::class.java) {
            val metricsBuilder = MetricsBuilder<Graphite>()
            (1..27).forEach {
                metricsBuilder.metric {
                    StringMetric("*")
                }
            }
        }
        Assertions.assertEquals(true, illegalStateException.message?.matches(Regex("Current implementation supports only.*")))

    }

    @Test
    fun `promQl metric with default legendFormat`() {
        val metricsBuilder = MetricsBuilder<PromQl>()
        val id = metricsBuilder.prometheusMetric(instant = true) {
            "metric_name{}".asInstantVector()
        }

        id shouldBeEqualTo "A"
        metricsBuilder.metrics[0].toJson().toString() shouldEqualToJson
                ("{\"refId\":\"A\",\"format\":\"time_series\",\"expr\":\"metric_name{}\",\"instant\":true,\"hide\":false}")
    }
}