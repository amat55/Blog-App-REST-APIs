package com.ahmetsenocak.blogapp.service;

import com.ahmetsenocak.blogapp.payload.CategoryDTO;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategory(Long categoryId);
}
