package com.ecommerce.project.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data  // this generates setter and getters
@JsonPropertyOrder({"categoryId", "categoryName"})
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = "Category name is mandatory")  // validates API input
    @NonNull                                             // Lombok null-safety
    @Size(min = 3, max = 50, message = "Category name must be between 3 and 15 characters")
    private String categoryName;



//    public Category() {
//    }
//
//    public Category(String categoryName) {
//        this.categoryName = categoryName;
//    }


//    This code is commented. Because this is now done my Lombok setter and getter
//    public long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
}
