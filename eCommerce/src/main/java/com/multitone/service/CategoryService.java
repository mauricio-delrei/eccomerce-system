package com.multitone.service;

import com.multitone.dto.CategoryDTO;
import com.multitone.model.Category;
import com.multitone.repository.CategoryRepository;
import com.multitone.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(Long id) {
        Optional<Category> categoryId = repository.findById(id);
        return categoryId.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id +
                ", Type: " + Category.class.getName()));
    }

    public Category addCategory(Category category) {
        category.setId(null);
        return repository.save(category);
    }

    public Category update(Category category) {
        Category newCostumer = findById(category.getId());
        updateData(newCostumer, category);
        return repository.save(newCostumer);
    }

    public void delete(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dIVE){
            throw new DataIntegrityViolationException("It is not possible to delete the category with associated products");
        }
    }

    public List<Category> findAll() {
        return (List<Category>) repository.findAll();
    }

    public Category fromDto(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    private void updateData(Category newCategory, Category category){
        newCategory.setName(category.getName());
    }

}
