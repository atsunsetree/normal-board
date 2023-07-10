package com.boardapplication.entity;

public enum Category {

    ABUSE("욕설"),
    LEWDNESS("음란"),
    LIBEL("비방");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}
