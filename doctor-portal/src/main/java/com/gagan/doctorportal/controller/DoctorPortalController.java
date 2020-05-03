/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2020-05-02 19:58:25
 * @modify date 2020-05-02 19:58:25
 * @desc [description]
 */
package com.gagan.doctorportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DoctorPortalController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Here we are refereing to a logical name of a microservice 
     * Using Round robin Algirith different instance will be called each time
     * 'doctor-service' is the microservice on ehich loadbalancing is perform
     * This is specified in main clas susing @RbbonClient("doctor-service")
     */
    @GetMapping("/doctors")
    public String getDoctors(){
        String url = "http://doctor-service-app/allDoctors";//http://localhost:9081/allDoctors";
        return restTemplate.getForObject(url, String.class);
    }
    
}