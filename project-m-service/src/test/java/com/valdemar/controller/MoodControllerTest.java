package com.valdemar.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class MoodControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new MoodController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/mood")
                        .param("token", "giI7FjIOBSQcqhV39OXKNFKB")
                        .param("team_id", "T0001")
                        .param("team_domain", "example")
                        .param("channel_id", "C2147483705")
                        .param("channel_name", "test")
                        .param("user_id", "U2147483697")
                        .param("user_name", "Steve")
                        .param("command", "/weather")
                        .param("text", "94070")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Ju's Project M SlackOutgoingData(token=giI7FjIOBSQcqhV39OXKNFKB, team_id=T0001, team_domain=example, channel_id=C2147483705, channel_name=test, user_id=U2147483697, user_name=Steve, command=/weather, text=94070)")));
    }

}