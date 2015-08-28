package com.valdemar.dao;

import lombok.Data;

import java.util.Date;

@Data(staticConstructor = "of")
public class Log {

    private final Long id;
    private final Long activityId;
    private final Date date;
    private final String notes;
}
