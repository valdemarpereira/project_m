package com.valdemar.dao.exceptions;

import lombok.Getter;

public class ActivityNotFoundException extends Exception {

    @Getter
    private final String name;

    public ActivityNotFoundException(String name) {
        this.name = name;
    }
}
