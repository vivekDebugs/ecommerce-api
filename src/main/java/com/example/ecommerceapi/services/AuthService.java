package com.example.ecommerceapi.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private RestTemplate restTemplate;
    private final static String AUTH_SERVICE_ENDPOINT="http://ecommerceuserservice/auth";

    public AuthService(@Qualifier("loadBalancedRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean fakeValidate() {
        return restTemplate.getForObject(AUTH_SERVICE_ENDPOINT + "/fake_validate", Boolean.class);
    }
}
