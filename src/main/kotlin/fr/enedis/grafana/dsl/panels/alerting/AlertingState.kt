package fr.enedis.grafana.dsl.panels.alerting

interface AlertingState {

    fun asState(): String
}
