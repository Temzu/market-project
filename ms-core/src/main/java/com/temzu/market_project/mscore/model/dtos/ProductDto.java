package com.temzu.market_project.mscore.model.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private CategoryDto categoryDto;
}
