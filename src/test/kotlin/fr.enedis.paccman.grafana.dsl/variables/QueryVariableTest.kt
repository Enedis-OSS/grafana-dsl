package fr.enedis.grafana.dsl.variables

import org.junit.jupiter.api.Test
import fr.enedis.grafana.dsl.datasource.Zabbix
import fr.enedis.grafana.dsl.jsonFile
import fr.enedis.grafana.dsl.shouldEqualToJson

class QueryVariableTest {

    @Test
    fun `should create variable with all properties set`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.query(datasource = Zabbix, query = "App version") {
            displayName = "Test"
            hidingMode = HidingMode.NONE
            multiValuesAllowed = true
            includeAllValue = true
            regex = ".*test.*"
            refreshMode = RefreshMode.NEVER
            sortOrder = SortOrder.DISABLED
            allValue = "test"
            current = CurrentVariableValue("test")
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties set`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.query(datasource = Zabbix, query = "App version")

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVariableWithMinProps.json")
    }

    @Test
    fun `should create variable with tags`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.query(datasource = Zabbix, query = "App version") {
            tags = VariableTags("*.*", "*.\$tag")
        }

        println(variable.toJson().toString())
        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVariableWithTags.json")
    }
}
