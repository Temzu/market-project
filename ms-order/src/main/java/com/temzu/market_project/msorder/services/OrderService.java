package com.temzu.market_project.msorder.services;

import com.temzu.market_project.msorder.entities.Cart;
import com.temzu.market_project.msorder.entities.Order;
import com.temzu.market_project.msorder.repositories.OrderRepository;
import com.temzu.market_project.routinglib.clients.ProductClient;
import com.temzu.market_project.routinglib.dtos.CartDto;
import com.temzu.market_project.routinglib.dtos.OrderDto;
import com.temzu.market_project.routinglib.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;

    private final ProductClient productClient;

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;


    @Transactional
    public Order createFromUserCart(Long userId, UUID cartUuid, String address) {
        CartDto cartDto = cartService.findById(cartUuid);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Order order = new Order(cart, userId, address);
        order = orderRepository.save(order);
        return order;
    }

    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id).get();
        List<Long> productIds = new ArrayList<>();
        order.getItems().forEach(item -> productIds.add(item.getProductId()));
        List<ProductDto> products = productClient.findProductsByIds(productIds);
        OrderDto orderDto = toDto(order);
        orderDto.setProducts(products);
        return orderDto;
    }

    public List<OrderDto> findAllOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private OrderDto toDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    private Order toEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

}
