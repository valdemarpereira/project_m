package com.valdemar.controller;

import com.valdemar.dao.Activity;
import com.valdemar.dao.exceptions.ActivityNotFoundException;
import com.valdemar.service.ActivityService;
import com.valdemar.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LogController {

    @Autowired
    LogService logService;

    @Autowired
    ActivityService activityService;

    @RequestMapping(
            value = "/log",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String log(
            SlackOutgoingData token) {

        String[] command = token.getText() != null? token.getText().toLowerCase().trim().split("\\s+", 2) : new String[0];
        List<Activity> availableActivities = activityService.getAll();


        if(command.length == 0 || command[0].isEmpty()){
            return "Missing activity name. Try /log help for the list of available activities: " +
                    parseAvailableActivities(availableActivities);
        }

        if(command[0].equals("help")){
            return "Available activities: " + parseAvailableActivities(availableActivities);
        }

        if(!availableActivities.stream().anyMatch(a -> a.getName().equalsIgnoreCase(command[0]))){
            return "Unavailable Activity. Try /log help for the list of available activities";
        }

        try {
            logService.log(command[0], (command.length > 1 ? command[1] : ""));
            return "Activity " + command[0] + " has been logged: Your Mood status should be around XXX %"; //TODO: Calculate moood
        } catch (ActivityNotFoundException e) {
            return "Unavailable Activity. Try /log help for the list of available activities";
        }
    }

    private String parseAvailableActivities(List<Activity> availableActivities){
        return availableActivities.stream().map(a -> a.getName()).collect(Collectors.joining(", "));
    }
}
