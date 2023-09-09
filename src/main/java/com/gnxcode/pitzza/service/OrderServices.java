package com.gnxcode.pitzza.service;

import com.gnxcode.pitzza.persitence.entity.OrderEntity;
import com.gnxcode.pitzza.persitence.projection.OrderSummary;
import com.gnxcode.pitzza.persitence.repository.OrderRepository;
import com.gnxcode.pitzza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServices {
    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }

    public List<OrderEntity> getByAfterDate(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOrderOut(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummary(int orderId){
        return orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
        return orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
