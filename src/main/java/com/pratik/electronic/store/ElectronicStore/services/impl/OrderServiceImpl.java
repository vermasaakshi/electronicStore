package com.pratik.electronic.store.ElectronicStore.services.impl;

import com.pratik.electronic.store.ElectronicStore.dtos.OrderDto;
import com.pratik.electronic.store.ElectronicStore.dtos.OrderItemDto;
import com.pratik.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.pratik.electronic.store.ElectronicStore.dtos.ProductDto;
import com.pratik.electronic.store.ElectronicStore.entities.Order;
import com.pratik.electronic.store.ElectronicStore.entities.OrderItem;
import com.pratik.electronic.store.ElectronicStore.helper.Helper;
import com.pratik.electronic.store.ElectronicStore.repositories.OrderRepository;
import com.pratik.electronic.store.ElectronicStore.repositories.UserRepository;
import com.pratik.electronic.store.ElectronicStore.services.OrderService;

import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        // Generate ID
        String orderId = UUID.randomUUID().toString();
        orderDto.setOrderId(orderId);

        // Set order date
        orderDto.setOrderDate(LocalDateTime.now());

        // Create the order entity
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setUserId(orderDto.getUserId());
        order.setCustomerName(orderDto.getCustomerName());
        order.setOrderDate(orderDto.getOrderDate());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        
        // Calculate total amount and create order items
        double totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        
        if (orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
            for (OrderItemDto itemDto : orderDto.getItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(UUID.randomUUID().toString());
                orderItem.setProductId(itemDto.getProductId());
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setTotalPrice(itemDto.getTotalPrice());
                orderItem.setOrder(order); // Set the parent reference
                
                orderItems.add(orderItem);
                totalAmount += itemDto.getTotalPrice();
            }
        }
        
        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Map back to DTO
        return mapOrderToDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return mapOrderToDto(order);
    }

    @Override
    public List<OrderDto> getOrdersByUser(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        orderRepository.delete(order);
    }
    
    // Helper method to convert Entity to DTO
    private OrderDto mapOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUserId());
        orderDto.setCustomerName(order.getCustomerName());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        
        List<OrderItemDto> itemDtos = new ArrayList<>();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                OrderItemDto itemDto = new OrderItemDto();
                // Map the id field to orderItemId in DTO
                itemDto.setOrderItemId(item.getId());
                itemDto.setProductId(item.getProductId());
                itemDto.setQuantity(item.getQuantity());
                itemDto.setTotalPrice(item.getTotalPrice());
                itemDtos.add(itemDto);
            }
        }
        
        orderDto.setItems(itemDtos);
        return orderDto;
    }
    
    // Helper method to convert DTO to Entity (if needed)
    private Order mapDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setUserId(orderDto.getUserId());
        order.setCustomerName(orderDto.getCustomerName());
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        
        List<OrderItem> items = new ArrayList<>();
        if (orderDto.getItems() != null) {
            for (OrderItemDto itemDto : orderDto.getItems()) {
                OrderItem item = new OrderItem();
                // Map orderItemId from DTO to id in entity
                item.setId(itemDto.getOrderItemId());
                item.setProductId(itemDto.getProductId());
                item.setQuantity(itemDto.getQuantity());
                item.setTotalPrice(itemDto.getTotalPrice());
                item.setOrder(order);
                items.add(item);
            }
        }
        
        order.setItems(items);
        return order;
    }

    //getAll orders
    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                     .map(this::mapOrderToDto)
                     .collect(Collectors.toList());
    }
    
}