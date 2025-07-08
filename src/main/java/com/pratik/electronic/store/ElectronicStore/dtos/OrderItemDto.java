package com.pratik.electronic.store.ElectronicStore.dtos;

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
public class OrderItemDto {

    private String orderItemId;   
    private String productId;   
    private int quantity;         
    private long totalPrice;       
}