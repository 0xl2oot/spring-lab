package com.imwyh.elastic;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author yonghong
 * @date 2019-07-12
 **/
@Slf4j
public class ElasticJestServiceTest extends ElasticApplicationTests {

    @Autowired
    private JestClient client;

    @Test
    public void jestTest() throws IOException {
        String query = "{\n"
                + "  \"aggs\": {\n"
                + "    \"2\": {\n"
                + "      \"terms\": {\n"
                + "        \"field\": \"my_name.keyword\",\n"
                + "        \"size\": 5,\n"
                + "        \"order\": {\n"
                + "          \"_term\": \"desc\"\n"
                + "        }\n"
                + "      },\n"
                + "      \"aggs\": {\n"
                + "        \"3\": {\n"
                + "          \"terms\": {\n"
                + "            \"field\": \"version.keyword\",\n"
                + "            \"size\": 5,\n"
                + "            \"order\": {\n"
                + "              \"_term\": \"desc\"\n"
                + "            },\n"
                + "            \"missing\": \"__missing__\"\n"
                + "          },\n"
                + "          \"aggs\": {\n"
                + "            \"4\": {\n"
                + "              \"terms\": {\n"
                + "                \"field\": \"platform.keyword\",\n"
                + "                \"size\": 5,\n"
                + "                \"order\": {\n"
                + "                  \"_term\": \"desc\"\n"
                + "                },\n"
                + "                \"missing\": \"__missing__\"\n"
                + "              },\n"
                + "              \"aggs\": {\n"
                + "                \"5\": {\n"
                + "                  \"terms\": {\n"
                + "                    \"field\": \"net_type.keyword\",\n"
                + "                    \"size\": 5,\n"
                + "                    \"order\": {\n"
                + "                      \"_term\": \"desc\"\n"
                + "                    },\n"
                + "                    \"missing\": \"__missing__\"\n"
                + "                  },\n"
                + "                  \"aggs\": {\n"
                + "                    \"6\": {\n"
                + "                      \"terms\": {\n"
                + "                        \"field\": \"device.keyword\",\n"
                + "                        \"size\": 5,\n"
                + "                        \"order\": {\n"
                + "                          \"_term\": \"desc\"\n"
                + "                        },\n"
                + "                        \"missing\": \"__missing__\"\n"
                + "                      }\n"
                + "                    }\n"
                + "                  }\n"
                + "                }\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  },\n"
                + "  \"size\": 0,\n"
                + "  \"_source\": {\n"
                + "    \"excludes\": []\n"
                + "  },\n"
                + "  \"stored_fields\": [\n"
                + "    \"*\"\n"
                + "  ],\n"
                + "  \"script_fields\": {},\n"
                + "  \"docvalue_fields\": [\n"
                + "    \"@timestamp\"\n"
                + "  ],\n"
                + "  \"query\": {\n"
                + "    \"bool\": {\n"
                + "      \"must\": [\n"
                + "        {\n"
                + "          \"match_all\": {}\n"
                + "        },\n"
                + "        {\n"
                + "          \"range\": {\n"
                + "            \"@timestamp\": {\n"
                + "              \"gte\": 1562917681203,\n"
                + "              \"lte\": 1562918581203,\n"
                + "              \"format\": \"epoch_millis\"\n"
                + "            }\n"
                + "          }\n"
                + "        }\n"
                + "      ],\n"
                + "      \"filter\": [],\n"
                + "      \"should\": [],\n"
                + "      \"must_not\": []\n"
                + "    }\n"
                + "  }\n"
                + "}";
        Search search = new Search.Builder(query).build();

        long startTime = System.currentTimeMillis();
        SearchResult result = client.execute(search);
        long endTime = System.currentTimeMillis();

        log.info("query success in {} ms", endTime - startTime);
        log.info(result.toString());
    }

    //@Test
    //public void jestTest2() throws IOException {
    //    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    //    searchSourceBuilder
    //            .aggregation(AggregationBuilders.avg("proc_cpu"))
    //            .aggregation(AggregationBuilders.avg("proc_mem"));
    //
    //    Search search = new Search.Builder(searchSourceBuilder.toString())
    //            .build();
    //    SearchResult result = client.execute(search);
    //    log.info(result.toString());
    //}
}