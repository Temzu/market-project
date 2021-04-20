package com.temzu.market_project.msorder.model.dtos;

import com.temzu.market.model.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private int pricePerItem;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerItem = orderItem.getPricePerItem();
        this.price = orderItem.getPrice();
    }
}
