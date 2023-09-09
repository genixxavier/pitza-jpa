package com.gnxcode.pitzza.web.controller;

import com.gnxcode.pitzza.persitence.entity.CustomerEntity;
import com.gnxcode.pitzza.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerServices customerServices;

    @Autowired
    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone){
        return ResponseEntity.ok(customerServices.findByPhone(phone));
    }
}
