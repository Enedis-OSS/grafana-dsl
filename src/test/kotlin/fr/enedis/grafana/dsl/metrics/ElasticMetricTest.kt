package fr.enedis.grafana.dsl.metrics

import fr.enedis.grafana.dsl.shouldEqualToJson
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ElasticMetricTest {
    @Test
    fun `Elastic metrics should be able to render bucket_script`() {
        // given
        val elasticMetricBuilder = ElasticMetric.Builder.Metric()

        // when
        val metrics = elasticMetricBuilder.metrics {
            metric {
                id = "1"
                field = "value_numeric"
                type = "max"
                hide = true
            }
            metric {
                id = "2"
                field = "1"
                type = "derivative"
                hide = true
            }
            metric {
                id = "3"
                pipelineVariable {
                    name = "var1"
                    pipelineAgg = "2"
                }
                bucket_script("if (params.var1 >0) { return params.var1 } else { return 0 }")
            }
        }

        // then
        metrics.count().shouldBeEqualTo(3)
        metrics[0].toJson().toString().shouldEqualToJson("""
            {
                "id":"1",
                "type":"max",
                "field":"value_numeric",
                "settings":{},
                "hide":true,
                "meta":{}
            }
         """)
        metrics[1].toJson().toString().shouldEqualToJson("""
            {
                "id":"2",
                "type":"derivative",
                "field":"1",
                "settings":{},
                "hide":true,
                "meta":{}
            }
        """)
        metrics[2].toJson().toString().shouldEqualToJson("""
            {
                "id":"3",
                "type":"bucket_script",
                "settings":{
                    "script":"if (params.var1 >0) { return params.var1 } else { return 0 }"
                },
                "hide":false,
                "pipelineVariables":[
                    {
                        "name":"var1",
                        "pipelineAgg":"2"
                    }
                ],
                "meta":{}
            }
        """)
    }
}
