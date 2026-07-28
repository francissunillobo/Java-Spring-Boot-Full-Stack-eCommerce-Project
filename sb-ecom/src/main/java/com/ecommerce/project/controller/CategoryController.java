package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CatergoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(AppConstants.API_BASE)
public class CategoryController {

    @Autowired
    private CatergoryService categoryService;

    //  We can also do this by using Autowiring.
    // CategoryController(CatergoryService categoryService){
    //     this.categoryService = categoryService;
    //  }

    @RequestMapping(value = AppConstants.PUBLIC_CATEGORIES, method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }


    @RequestMapping(value = AppConstants.PUBLIC_CATEGORIES, method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category)  // valid validate the incoming request.
    {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }


    @RequestMapping(value = AppConstants.ADMIN_CATEGORIES + "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category " + categoryId + " deleted successfully");
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @RequestMapping(value = AppConstants.PUBLIC_CATEGORIES + "/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
        try {
            category.setCategoryId(categoryId);
            categoryService.updateCategory(category, categoryId);
            return ResponseEntity.ok("Category " + categoryId + " updated successfully");
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}

