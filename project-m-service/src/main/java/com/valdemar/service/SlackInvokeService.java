package com.valdemar.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by valdemar.pereira on 28/08/15.
 */
@Service
public class SlackInvokeService {

    public void notify(String exercise){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("https://hooks.slack.com/services/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", new Payload(exercise), Payload.class);
    }

    @Data
    private class  Payload{
        private final String channel = "#project-m";
        private final String username = "judita_mood";
        private final String text;

        public Payload(String exercise){
            text = "Judita just logged " + exercise;
        }
    }
}
