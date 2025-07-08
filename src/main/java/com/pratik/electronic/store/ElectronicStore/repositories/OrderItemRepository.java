package com.pratik.electronic.store.ElectronicStore.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pratik.electronic.store.ElectronicStore.entities.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    List<OrderItem> findByOrder_OrderId(String orderId);
}
