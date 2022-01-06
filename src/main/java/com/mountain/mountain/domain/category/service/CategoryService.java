package com.mountain.mountain.domain.category.service;

import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category findCateByNo(Long cateId) {
        Optional<Category> category = categoryRepository.findById(cateId);

        return category.get();
    }
}
