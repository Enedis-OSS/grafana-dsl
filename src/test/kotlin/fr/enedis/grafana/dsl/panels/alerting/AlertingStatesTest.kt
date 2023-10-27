package fr.enedis.grafana.dsl.panels.alerting

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class AlertingStatesTest {

    @ParameterizedTest
    @MethodSource("alertingStates")
    fun `should serialize alerting states correctly`(alertingState: AlertingState, representation: String) {
        alertingState.asState() shouldBeEqualTo representation
    }

    companion object {
        @JvmStatic
        fun alertingStates() = arrayOf(
            arrayOf(Alerting, "alerting"),
            arrayOf(Ok, "ok"),
            arrayOf(KeepLastState, "keep_state"),
            arrayOf(NoData, "no_data")
        )
    }
}
