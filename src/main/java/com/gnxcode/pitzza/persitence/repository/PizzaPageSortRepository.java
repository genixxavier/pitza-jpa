package com.gnxcode.pitzza.persitence.repository;

import com.gnxcode.pitzza.persitence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPageSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
