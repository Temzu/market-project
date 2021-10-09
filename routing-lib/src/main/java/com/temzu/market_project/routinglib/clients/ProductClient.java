package com.temzu.market_project.routinglib.clients;

import com.temzu.market_project.routinglib.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("ms-product")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDto getById(@PathVariable Long id);

    @GetMapping("/ids")
    List<ProductDto> findProductsByIds(@RequestParam List<Long> ids);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto add(@RequestBody ProductDto product);

    @PutMapping()
    ProductDto update(@RequestBody ProductDto product);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
