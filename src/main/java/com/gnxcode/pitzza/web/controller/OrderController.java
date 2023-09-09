package com.gnxcode.pitzza.web.controller;

import com.gnxcode.pitzza.persitence.entity.OrderEntity;
import com.gnxcode.pitzza.persitence.projection.OrderSummary;
import com.gnxcode.pitzza.service.OrderServices;
import com.gnxcode.pitzza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderServices orderServices;

    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> orders(){
        return ResponseEntity.ok(orderServices.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(orderServices.getByAfterDate());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOrderOutside(){
        return ResponseEntity.ok(orderServices.getOrderOut());
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String idCustomer){
        return ResponseEntity.ok(orderServices.getCustomerOrders(idCustomer));
    }

    @GetMapping("/summary/{idOrder}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int idOrder){
        return ResponseEntity.ok(orderServices.getSummary(idOrder));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok(orderServices.saveRandomOrder(dto));
    }
}
