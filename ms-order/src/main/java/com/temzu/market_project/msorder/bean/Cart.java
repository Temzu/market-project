package com.temzu.market_project.msorder.bean;

import com.temzu.market_project.mscore.exceptions.ProductNotFoundException;
import com.temzu.market_project.msorder.model.entities.OrderItem;
import com.temzu.market_project.msproduct.model.entities.Product;
import com.temzu.market_project.msproduct.services.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class Cart {
    private final ProductService productService;

    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        items.stream().filter(item -> item.getProduct().getId().equals(id))
                .findFirst().ifPresentOrElse(
                        orderItem -> {
                            orderItem.incrementQuantity();
                            recalculate();
                        },
                        () -> {
                            Product product = productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
                            OrderItem orderItem = new OrderItem(product);
                            items.add(orderItem);
                            recalculate();
                        }
        );
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        items.forEach(item -> totalPrice += item.getPrice());
    }
}
