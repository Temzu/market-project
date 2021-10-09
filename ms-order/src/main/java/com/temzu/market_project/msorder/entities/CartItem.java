package com.temzu.market_project.msorder.entities;

import com.temzu.market_project.routinglib.dtos.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private BigDecimal pricePerProduct;

    @Column(name = "price")
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public CartItem(ProductDto product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = this.pricePerProduct;
    }

    public void incrementQuantity() {
        quantity++;
        price =  pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }

    public void incrementQuantity(int amount) {
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }

    public void decrementQuantity() {
        quantity--;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }

}
