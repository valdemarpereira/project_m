package com.valdemar.dao;

import lombok.Data;

@Data(staticConstructor = "of")
public class Activity {

    private final Long id;
    private final String name;
    private final String description;
    private final Long categoryId;
    private final boolean active;
}
