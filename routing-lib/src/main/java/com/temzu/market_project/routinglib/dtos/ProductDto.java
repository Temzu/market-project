package com.temzu.market_project.routinglib.dtos;

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
}