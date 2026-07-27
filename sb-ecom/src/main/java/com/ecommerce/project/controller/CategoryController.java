package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CatergoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CatergoryService categoryService;

    //  We can also do this by using Autowiring.
    // CategoryController(CatergoryService categoryService){
    //     this.categoryService = categoryService;
    //  }

    @GetMapping("api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        //return ResponseEntity.ok(categoryService.createCategory(category));
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category " + categoryId + " deleted successfully");
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("api/public/categories/{categoryId}")
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

