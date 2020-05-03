/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-05-02 19:54:23
 * @modify date 2020-05-02 19:54:23
 * @desc [description]
 */
package com.gagan.doctorportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The name inside the @RibbonClient must be the same as in properties 
 * It is a way of specifying one common logical name for multiple instances of a microservice 
 * @LoadBalancer will send request to different instance of same Microservice using round robin algorithm
 */
@SpringBootApplication
// @RibbonClient("doctor-service")
@EnableDiscoveryClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	// @LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
