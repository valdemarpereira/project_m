package com.valdemar.controller;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SlackOutgoingData {

    private String token;
    private String team_id;
    private String team_domain;
    private String channel_id;
    private String channel_name;
    private String user_id;
    private String user_name;
    private String command;
    private String text;
}
