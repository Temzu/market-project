package com.temzu.market_project.msproduct.controllers;

import com.temzu.market_project.mscore.exceptions.InvalidPageException;
import com.temzu.market_project.mscore.exceptions.ProductNotFoundException;
import com.temzu.market_project.msproduct.model.dtos.ProductDto;
import com.temzu.market_project.msproduct.model.entities.Product;
import com.temzu.market_project.msproduct.repositories.specifications.ProductSpecifications;
import com.temzu.market_project.msproduct.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> getAll(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String[] sort) {
        if (page < 1) throw new InvalidPageException(page.toString());
        return productService.getAll(ProductSpecifications.build(params), page - 1, size, Optional.of(sort));
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    @GetMapping("/ids")
    List<ProductDto> findProductsByIds(@RequestParam List<Long> ids) {
        return productService.getByIds(ids);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
