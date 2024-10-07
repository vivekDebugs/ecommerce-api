package com.example.ecommerceapi.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean(name = "simpleRestTemplate")
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "loadBalancedRestTemplate")
    @LoadBalanced
    public RestTemplate createLoadBalancedRestTemplate() {
        return new RestTemplate();
    }
}
