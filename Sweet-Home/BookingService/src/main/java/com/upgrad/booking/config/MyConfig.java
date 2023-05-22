package com.upgrad.booking.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        return  new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }



}
