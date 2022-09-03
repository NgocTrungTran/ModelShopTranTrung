package com.tnt.modelshoptrantrung.service.category;


import com.tnt.modelshoptrantrung.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Category getById(Long id) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
