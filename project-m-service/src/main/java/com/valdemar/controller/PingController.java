package com.valdemar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by valdemar.pereira on 07/08/15.
 */

@RestController
public class PingController {

    @RequestMapping("/ping")
    public String ping() {
        return "Greetings from Spring Boot!";
    }
}
