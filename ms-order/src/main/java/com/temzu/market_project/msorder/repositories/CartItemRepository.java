package com.temzu.market_project.msorder.repositories;

import com.temzu.market_project.msorder.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> { }