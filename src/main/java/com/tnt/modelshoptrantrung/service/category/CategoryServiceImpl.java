package com.tnt.modelshoptrantrung.service.category;


import com.tnt.modelshoptrantrung.model.Category;
import com.tnt.modelshoptrantrung.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById ( id );
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save ( category );
    }

    @Override
    public void delete(Long id) {

    }
}
