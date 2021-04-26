package com.temzu.market_project.mscore.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {

    private List<OrderItemDto> items;

    private int totalPrice;

}
