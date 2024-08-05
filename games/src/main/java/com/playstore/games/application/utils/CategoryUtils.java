package com.playstore.games.application.utils;

import com.playstore.games.application.exception.CategoryNotFoundException;
import com.playstore.games.domain.ECategory;

public class CategoryUtils {

    public static ECategory setCategory(String category) throws CategoryNotFoundException {

        try {
            return ECategory.valueOf(category.toUpperCase());
        } catch (Exception e) {
            throw new CategoryNotFoundException("Category not found");
        }
    }

}
