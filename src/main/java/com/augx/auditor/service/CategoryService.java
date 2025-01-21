package com.augx.auditor.service;

import com.augx.auditor.model.dto.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    public void createOrUpdateCategory(CategoryDto categoryDto);
    public List<CategoryDto> getCategoryByName(String name);
    public void deleteCategory(UUID id);

}
