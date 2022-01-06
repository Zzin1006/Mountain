package com.mountain.mountain.domain.category.service;

import com.mountain.mountain.controller.category.dto.ResponseCategoryDTO;
import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category findCateByNo(Category cateId) {
        Optional<Category> category = categoryRepository.findById(cateId.getId());

        return category.get();
    }


    public Page<Category> findAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
