package com.boardapplication.common;

import com.core.entity.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        return Category.valueOf(source);
    }
}
