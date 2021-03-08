package com.kunlun.basedata.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zipkin.storage.elasticsearch")
public class ElasticSearchConfig {

    private String hosts;

    @Bean
    public RestHighLevelClient client() {
        String[] array = hosts.split(":");
        int port = Integer.parseInt(array[1]);
        return new RestHighLevelClient(RestClient.builder(new HttpHost(array[0], port, "http")));
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }
}
