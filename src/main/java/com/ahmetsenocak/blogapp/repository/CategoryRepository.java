package com.ahmetsenocak.blogapp.repository;

import com.ahmetsenocak.blogapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
