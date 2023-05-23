package com.multitone.repository;

import com.multitone.model.Category;
import com.multitone.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
    Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);

}
