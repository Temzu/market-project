package com.temzu.market_project.routinglib.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private String productTitle;

    private int quantity;

    private int pricePerItem;

    private int price;
}
