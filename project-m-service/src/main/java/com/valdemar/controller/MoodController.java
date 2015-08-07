package com.valdemar.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoodController {

    @RequestMapping(value = "/mood", method = RequestMethod.POST)
    public String mood(@RequestBody String data) {
        //@RequestBody Bookmark input
        return "Ju's Project M";
    }

}
