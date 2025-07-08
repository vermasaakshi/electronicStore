package com.pratik.electronic.store.ElectronicStore.repositories;

import com.pratik.electronic.store.ElectronicStore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUserId(String userId); 
    List<Order> findAll();

}
