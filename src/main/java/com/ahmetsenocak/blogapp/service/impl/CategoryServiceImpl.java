package com.ahmetsenocak.blogapp.service.impl;

import com.ahmetsenocak.blogapp.entity.Category;
import com.ahmetsenocak.blogapp.exception.ResourceNotFoundException;
import com.ahmetsenocak.blogapp.payload.CategoryDTO;
import com.ahmetsenocak.blogapp.repository.CategoryRepository;
import com.ahmetsenocak.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category saveCategory = categoryRepository.save(category);

        return modelMapper.map(saveCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return modelMapper.map(category, CategoryDTO.class);
    }
}
