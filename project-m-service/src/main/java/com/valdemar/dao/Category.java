package com.valdemar.dao;

import lombok.Data;

@Data
public class Category {

    private final Long id;
    private final String category;
    private final String description;
    private final boolean active;
}
