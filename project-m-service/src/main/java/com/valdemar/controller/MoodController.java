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

        String[] command = token.getText().toLowerCase().split("\\s+");

        if(command.length == 0){
            return "Missing Arg. Try /mood help";
        }



        switch (command[0]){
            case "help":
                return "Judita' Mood App. [ping, stats, log, more_to_come...]";
            case "ping":
                return "pong...";
            case "valdemar":
                return "lover";
            case "stats":
                return "stats for the last 7 days";
            case "log":
                return "lets log something";
            default:
                return "Missing Arg";
        }
    }
}
