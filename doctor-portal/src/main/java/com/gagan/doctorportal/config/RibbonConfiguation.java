package com.gagan.doctorportal.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Use to change default Round Robin method of load Balancing with Ribbon Implementations
 * *****Simply Uncomment @Config to anble this setting 
 * It doesnt require @RibbonClient 
 * It is a part of LoadBalancing which is configured by RibbonClient dependencies
 */
// @Configuration
public class RibbonConfiguation {

    @Bean
    public IRule getIRule(){
        return new RandomRule();
    }
    
}