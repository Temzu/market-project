package com.temzu.market_project.msproduct.model.dtos;

import com.temzu.market_project.msproduct.model.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {
    private Long id;
    private String title;
    private BigDecimal price;
    private String category;

    public ProductDto(Product p) {
        id = p.getId();
        title = p.getTitle();
        price = p.getPrice();
        category = p.getCategories().toString();
    }
}