package com.temzu.market_project.routinglib.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private String productTitle;

    private int quantity;

    private int pricePerProduct;

    private BigDecimal price;

}
