package com.temzu.market_project.routinglib.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {

    private List<OrderItemDto> items;

    private int totalPrice;

}
