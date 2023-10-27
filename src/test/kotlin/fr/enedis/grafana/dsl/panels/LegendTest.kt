package fr.enedis.grafana.dsl.panels

import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson
import org.junit.jupiter.api.Test

/**
 * [Legend] Test
 */
class LegendTest {

    @Test
    fun `should create default legend block`() {
        Legend.DEFAULT.toJson().toString() shouldEqualToJson jsonFile("DefaultLegendBlock.json")
    }

    @Test
    fun `should create empty legend block`() {
        Legend.EMPTY.toJson().toString() shouldEqualToJson jsonFile("EmptyLegendBlock.json")
    }

    @Test
    fun `should create custom legend block`() {

        val legend = Legend(alignAsTable = true,
                avg = false,
                current = true,
                hideEmpty = true,
                hideZero = true,
                max = true,
                min = false,
                rightSide = false,
                show = true,
                sort = null,
                sortDesc = false,
                total = false,
                values = true
        )
        legend.toJson().toString() shouldEqualToJson jsonFile("CustomLegendBlock.json")
    }
}