package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategory(Long categoryId);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryID);

    void deleteCategory(Long categoryID);
}
