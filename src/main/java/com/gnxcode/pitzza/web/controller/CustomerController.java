package com.gnxcode.pitzza.web.controller;

import com.gnxcode.pitzza.persitence.entity.CustomerEntity;
import com.gnxcode.pitzza.persitence.entity.OrderEntity;
import com.gnxcode.pitzza.service.CustomerServices;
import com.gnxcode.pitzza.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerServices customerServices;
    private final OrderServices orderServices;

    @Autowired
    public CustomerController(CustomerServices customerServices, OrderServices orderServices) {
        this.customerServices = customerServices;
        this.orderServices = orderServices;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone){
        return ResponseEntity.ok(customerServices.findByPhone(phone));
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        return ResponseEntity.ok(orderServices.getCustomerOrders(idCustomer));
    }
}
