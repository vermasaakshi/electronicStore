package com.pratik.electronic.store.ElectronicStore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_item")
public class OrderItem {
    
    @Id
    private String id;  // Match your database column name
    
    @Column(name = "product_id")
    private String productId;
    
    private int quantity;
    
    @Column(name = "total_price")
    private long totalPrice;  // Changed to long to match DTO
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}