package com.pratik.electronic.store.ElectronicStore.dtos;

import java.time.LocalDateTime;

import java.util.List;

import com.pratik.electronic.store.ElectronicStore.validators.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String orderId;
    private String userId;
    private String customerName;
    private LocalDateTime orderDate;
    private double totalAmount;
    private List<OrderItemDto> items;
    private String paymentMethod;
}
