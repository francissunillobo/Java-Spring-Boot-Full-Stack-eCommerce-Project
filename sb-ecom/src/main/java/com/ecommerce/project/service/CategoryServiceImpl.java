package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CatergoryService {
    private long nextId = 1;
    private List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
        return "Category created successfully";
    }

    @Override
    public String deleteCategory(long categoryId) {
//        if (!categories.removeIf(category -> category.getCategoryId() == categoryId)) {
//            return "Category " + categoryId + " not found";
//        }
//        return "Category " + categoryId + " deleted successfully";
//    }
        Category category = getCategory(categoryId);
        categories.remove(category);
        return "Category " + categoryId + " deleted successfully";
    }
    @Override
    public String updateCategory(Category category, long categoryId ) {

        // Another way of doing this
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst();

        if(optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
        }
        else {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Category " + categoryId + " not found");
        }
        return "Category " + categoryId + " updated successfully";
    }

    public Category getCategory(long categoryId )
    {
        return categories.stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Category " + categoryId + " not found"));
    }

}
