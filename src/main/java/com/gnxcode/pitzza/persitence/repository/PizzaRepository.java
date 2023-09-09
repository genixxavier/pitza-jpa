package com.gnxcode.pitzza.persitence.repository;

import com.gnxcode.pitzza.persitence.entity.PizzaEntity;
import com.gnxcode.pitzza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

    int countByVeganTrue();

    @Query(value = "UPDATE pizza SET price = :#{#newPricePizza.newPrice} WHERE id_pizza = :#{#newPricePizza.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPricePizza") UpdatePizzaPriceDto newPricePizza);
}
