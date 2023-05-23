package com.multitone.resources;


import com.multitone.model.PurchaseOrder;
import com.multitone.service.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class PurchaseOrderResource {

    private final PurchaseOrderService service;

    public PurchaseOrderResource(PurchaseOrderService service) {
        this.service = service;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<PurchaseOrder> find(@PathVariable Long id){
        PurchaseOrder findById = service.findById(id);
        return ResponseEntity.ok().body(findById);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody PurchaseOrder obj) {
        obj = service.addOrder(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>>getAllOrders(){
        var orders = service.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
