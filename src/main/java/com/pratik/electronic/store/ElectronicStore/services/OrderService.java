package com.pratik.electronic.store.ElectronicStore.services;

import com.pratik.electronic.store.ElectronicStore.dtos.OrderDto;

import java.util.List;

public interface OrderService {

    // Create a new order
    OrderDto createOrder(OrderDto orderDto);

    // Get order by ID
    OrderDto getOrderById(String orderId);

    // Get orders by user ID
    List<OrderDto> getOrdersByUser(String userId);

     // Get all orders
     List<OrderDto> getAll();

    // Delete an order
    void deleteOrder(String orderId);
}
