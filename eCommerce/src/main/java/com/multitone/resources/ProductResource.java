package com.multitone.resources;

import com.multitone.dto.ProductDTO;
import com.multitone.model.Product;
import com.multitone.resources.utils.URL;
import com.multitone.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Long id){
        Product findById = productService.findById(id);
        return ResponseEntity.ok().body(findById);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "24")Integer linePerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {

        String nameDecoded = URL.decodeParam(name);
        List<Long> ids = URL.decodeIntList(categories);
        Page<Product> listPage = productService.search(name, ids, page, linePerPage, orderBy, direction);
        Page<ProductDTO> pageDto = listPage.map(ProductDTO::new);
        return ResponseEntity.ok().body(pageDto);
    }

}
