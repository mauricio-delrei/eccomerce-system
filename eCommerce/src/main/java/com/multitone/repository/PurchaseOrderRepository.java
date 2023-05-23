package com.multitone.repository;

import com.multitone.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder,Long> {
}
