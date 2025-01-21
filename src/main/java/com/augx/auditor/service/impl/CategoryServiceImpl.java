package com.augx.auditor.service.impl;

import com.augx.auditor.model.Category;
import com.augx.auditor.model.dto.CategoryDto;
import com.augx.auditor.repository.CategoryRepository;
import com.augx.auditor.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public void createOrUpdateCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        if (null == category.getId() || categoryRepository.existsById(category.getId())) {
            categoryRepository.save(category);
        }
    }

    public List<CategoryDto> getCategoryByName(String name) {
        List<Category> categories = (name != null && !name.isEmpty())
                ? categoryRepository.findAllByName(name)
                : categoryRepository.findAll();
        return modelMapper.map(categories, new TypeToken<List<CategoryDto>>() {
        }.getType());
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
