package com.valdemar.controller;

import com.valdemar.dao.Activity;
import com.valdemar.dao.exceptions.ActivityNotFoundException;
import com.valdemar.service.ActivityService;
import com.valdemar.service.LogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class LogControllerTest {

    @Mock
    LogService mockLogService;

    @Mock
    ActivityService mockActivityService;

    @InjectMocks
    LogController logController;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(logController).build();

        setUpActivityServiceMock();
        setUpLogServiceMock();
    }

    private void setUpActivityServiceMock() {

        when(mockActivityService.getAll()).
                thenReturn(
                        Stream.of(
                                Activity.of(1L, "Sprint", "Sprint Desc", 1L, true),
                                Activity.of(2L, "Yoga", "Yoga Desc", 1L, true),
                                Activity.of(3L, "Run", "Run Desc", 1L, true)
                        ).collect(Collectors.toList()));

    }

    private void setUpLogServiceMock() {


    }

    @Test
    public void logExerciseWithoutDescription() throws Exception {

        mvc.perform(
                getRequestBuilder()
                        .param("text", "run")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Activity run has been logged: Your Mood status should be around XXX %")));

        verify(mockLogService).log("run", "");
    }

    @Test
    public void logExerciseWithDescription() throws Exception {

        mvc.perform(
                getRequestBuilder()
                        .param("text", "run some description")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Activity run has been logged: Your Mood status should be around XXX %")));

        verify(mockLogService).log("run", "some description");

    }

    @Test
    public void logExerciseWithHelp() throws Exception {

        mvc.perform(
                getRequestBuilder()
                        .param("text", "help")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Available activities: Sprint, Yoga, Run")));
    }

    @Test
    public void logExerciseWithNoExercise() throws Exception {

        mvc.perform(
                getRequestBuilder()
                        .param("text", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Missing activity name. Try /log help for the list of available activities: Sprint, Yoga, Run")));
    }

    @Test
    public void logInvalidExercise() throws Exception {

        doThrow(new ActivityNotFoundException("invalid_act")).when(mockLogService).log("invalid_act", "");

        mvc.perform(
                getRequestBuilder()
                        .param("text", "invalid_act")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Unavailable Activity. Try /log help for the list of available activities")));
    }

    private MockHttpServletRequestBuilder getRequestBuilder() {
        return MockMvcRequestBuilders.post("/log")
                .param("token", "giI7FjIOBSQcqhV39OXKNFKB")
                .param("team_id", "T0001")
                .param("team_domain", "example")
                .param("channel_id", "C2147483705")
                .param("channel_name", "test")
                .param("user_id", "U2147483697")
                .param("user_name", "Steve")
                .param("command", "/log");
    }

}