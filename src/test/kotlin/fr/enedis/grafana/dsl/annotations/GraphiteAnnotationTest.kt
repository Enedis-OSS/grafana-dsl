package fr.enedis.grafana.dsl.annotations

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.metrics.functions.aliasByNode
import fr.enedis.grafana.dsl.panels.Color
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

/**
 * @author Ilya Doroshenko
 * @since 23.12.2019
 */
class GraphiteAnnotationTest {

    @Test
    fun `should create Graphite annotation`() {
        val builder = AnnotationBuilder(name = "test graphite")

        builder.enabled = true
        builder.color = Color.RED

        val annotation = builder.graphite {
            targetQuery = "*.*.some_event".aliasByNode(2, 3)
        }

        annotation.toJson().toString() shouldEqualToJson jsonFile("GraphiteAnnotation.json")
    }
}