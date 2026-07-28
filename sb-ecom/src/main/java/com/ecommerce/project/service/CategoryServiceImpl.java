package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CatergoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    public CategoryServiceImpl(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return "Category created successfully";
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public String deleteCategory(Long categoryId) {
//        if (!categories.removeIf(category -> category.getCategoryId() == categoryId)) {
//            return "Category " + categoryId + " not found";
//        }
//        return "Category " + categoryId + " deleted successfully";
//    }
        categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        categoryRepository.deleteById(categoryId);
        return "Category " + categoryId + " deleted successfully";
    }
    @Override
    public String updateCategory(Category category, long categoryId ) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        existingCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existingCategory);
        return "Category " + categoryId + " updated successfully";
    }

}
