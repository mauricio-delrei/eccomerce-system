package com.multitone.repository;

import com.multitone.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    @Transactional(readOnly = true)
    Customer findByEmail(String email);
}
