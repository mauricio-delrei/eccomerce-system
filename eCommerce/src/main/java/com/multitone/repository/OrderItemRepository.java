package com.multitone.repository;

import com.multitone.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}
