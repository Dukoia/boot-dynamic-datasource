//package com.dukoia.boot.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//
///**
// * @Description: RestClientConfig
// * @Author: jiangze.He
// * @Date: 2021-07-09
// * @Version: v1.0
// */
//@Configuration
//public class RestClientConfig extends AbstractElasticsearchConfiguration {
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("172.31.84.11:9200")
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//}