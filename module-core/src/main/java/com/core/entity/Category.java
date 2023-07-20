package com.core.entity;

import lombok.Getter;

@Getter
public enum Category {

    ABUSE("욕설"),
    LEWDNESS("음란"),
    LIBEL("비방");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public static String getCategoryDescription(int categoryInt) {
        for(Category category : Category.values()) {
            if(category.ordinal() == categoryInt) {
                return category.description;
            }
        }
        return null;
    }

    public String getValue() {
        return description;
    }
}
