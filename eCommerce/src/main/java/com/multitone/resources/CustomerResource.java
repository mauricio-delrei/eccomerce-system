package com.multitone.resources;


import com.multitone.dto.CustomerDTO;
import com.multitone.dto.CustomerNewDTO;
import com.multitone.model.Customer;
import com.multitone.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer customerId = service.findById(id);
        return ResponseEntity.ok().body(customerId);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<Customer> findByEmail(@RequestParam(value = "value") String email) {
        Customer obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        Customer customer = service.fromDto(customerDTO);
        customer.setId(id);
        customer = service.update(customer);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO objDto) {
        Customer customer = service.fromDto(objDto);
        customer = service.addCustomer(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> findAll() {

       var list = service.findAll();

        List<CustomerDTO> listDto = list.stream().map(CustomerDTO::new).toList();

        return ResponseEntity.ok().body(listDto);

    }

}
