package com.multitone.resources;

import com.multitone.dto.CategoryDTO;
import com.multitone.model.Category;
import com.multitone.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    private final CategoryService service;

    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Category findById = service.findById(id);

        return ResponseEntity.ok().body(findById);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = service.fromDto(categoryDTO);
        category = service.addCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = service.fromDto(categoryDTO);
        category.setId(id);
        category = service.update(category);
        return ResponseEntity.noContent().build();

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll() {

        List<Category> list = service.findAll();

        List<CategoryDTO> listDto = list.stream().map(CategoryDTO::new).toList();

        return ResponseEntity.ok().body(listDto);
    }
}
