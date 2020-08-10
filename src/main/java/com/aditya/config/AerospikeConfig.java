package com.aditya.config;

import com.aerospike.client.AerospikeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AerospikeConfig {

    @Bean
    public AerospikeClient getClient() {
        AerospikeClient client = new AerospikeClient("172.28.128.4", 3000);
        return client;
    }
}
