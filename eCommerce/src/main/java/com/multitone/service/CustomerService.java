package com.multitone.service;

import com.multitone.dto.CustomerDTO;
import com.multitone.dto.CustomerNewDTO;
import com.multitone.model.Address;
import com.multitone.model.Customer;
import com.multitone.repository.AddressRepository;
import com.multitone.repository.CustomerRepository;
import com.multitone.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;


    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer findById(Long id) {
        Optional<Customer> customerId = customerRepository.findById(id);
        return customerId.orElseThrow(() -> new ObjectNotFoundException("Object is not valid!"));

    }

    @Transactional
    public Customer addCustomer(Customer customer) {
        customer.setId(null);
        customer = customerRepository.save(customer);
        addressRepository.saveAll(customer.getAddresses());
        return customer;
    }

    public Customer update(Customer customer) {
        Customer newCustomer = findById(customer.getId());
        updateData(newCustomer, customer);
        return customerRepository.save(newCustomer);
    }

    public void delete(final Long id) {
        findById(id);
        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException dIVE) {
            throw new DataIntegrityViolationException("It is not possible to delete, there are associated entities");
        }
    }

    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer findByEmail(String email) {

        Customer obj = customerRepository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException("Object not found! Mail: " + email + ", " +
                    "Type: " + Customer.class.getName());
        }
        return obj;
    }

    public Customer fromDto(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(),null );
    }
    public Customer fromDto(CustomerNewDTO objDto) {
        Customer customer = new Customer(null, objDto.getName(), objDto.getEmail(), null);
        Address address = new Address(null, objDto.getAddress_line_1(), objDto.getAddress_line_2(), objDto.getTown_city(),
                objDto.getPostcode());
        customer.getAddresses().add(address);
        customer.getPhones().add(objDto.getMobile());
        if (objDto.getPhone() != null) {
            customer.getPhones().add(objDto.getPhone());
        }

        return customer;
    }
    private void updateData(Customer newCustomer, Customer customer) {
        newCustomer.setName(customer.getName());
        newCustomer.setEmail(customer.getEmail());
    }

}
