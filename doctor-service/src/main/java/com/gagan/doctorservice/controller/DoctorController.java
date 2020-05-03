package com.gagan.doctorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private Environment environment;

    private int counter;

    @GetMapping("/allDoctors")
    public String doctors() {
        counter ++;
        return counter + " ==> List of doctors new [ response from " + port + "]" + environment.getProperty("spring.application.name");
    }
    
}