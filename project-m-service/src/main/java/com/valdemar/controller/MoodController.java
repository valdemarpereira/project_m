package com.valdemar.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoodController {

    private static final String KEY = "giI7FjIOBSQcqhV39OXKNFKB";

    @RequestMapping(
            value = "/mood",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String mood(
            SlackOutgoingData token) {


        return "Ju's Project M " + token.toString();
    }
}
