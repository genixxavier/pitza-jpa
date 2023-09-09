package com.gnxcode.pitzza.service;

import com.gnxcode.pitzza.persitence.entity.CustomerEntity;
import com.gnxcode.pitzza.persitence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone){
        return customerRepository.findByPhone(phone);
    }
}
