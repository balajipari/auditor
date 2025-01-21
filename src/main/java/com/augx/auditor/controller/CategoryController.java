package com.augx.auditor.controller;

import com.augx.auditor.model.dto.CategoryDto;
import com.augx.auditor.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public void createOrUpdateCategory(@RequestBody CategoryDto category) {
        categoryService.createOrUpdateCategory(category);
    }

    @GetMapping
    public List<CategoryDto> getAllCategoryByName(@RequestParam(name = "name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteCatgeory(@PathVariable("id") UUID id) {
        categoryService.deleteCategory(id);
    }
}
