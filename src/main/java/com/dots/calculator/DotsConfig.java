package com.dots.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DotsConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}