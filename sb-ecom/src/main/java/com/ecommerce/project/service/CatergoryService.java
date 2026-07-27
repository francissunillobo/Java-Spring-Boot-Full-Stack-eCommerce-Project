package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CatergoryService {


    List<Category> getAllCategories();
    String createCategory(Category category);
    String deleteCategory(long categoryId);

    String updateCategory(Category category, long categoryId );
}
