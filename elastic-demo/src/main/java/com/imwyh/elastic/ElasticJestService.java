package com.imwyh.elastic;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author yonghong
 * @date 2019-07-12
 **/
@Slf4j
@Service
public class ElasticJestService {

    @Value("${spring.elasticsearch.jest.uris}")
    private String SERVER_URI;

    @Value("${spring.elasticsearch.jest.username}")
    private String USERNAME;

    @Value("${spring.elasticsearch.jest.password}")
    private String PASSWORD;

    @Bean
    public JestClient jestClient() {
        log.info("server_uri: {}, username: {}, password: {}", SERVER_URI, USERNAME, PASSWORD);
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(SERVER_URI)
                .multiThreaded(true)
                .connTimeout(50000)
                .readTimeout(50000)
                .defaultCredentials(USERNAME, PASSWORD)
                .build());
        log.info("jestClient init success");
        return factory.getObject();
    }
}
