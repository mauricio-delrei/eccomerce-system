package com.multitone.service;

import com.multitone.model.Category;
import com.multitone.model.Product;
import com.multitone.repository.CategoryRepository;
import com.multitone.repository.ProductRepository;
import com.multitone.service.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product findById(Long id) {
        Optional<Product> Id = productRepository.findById(id);
        return Id.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id +
                ", Type: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Long> ids, Integer page, Integer linePerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categories = (List<Category>) categoryRepository.findAllById(ids);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }

}
