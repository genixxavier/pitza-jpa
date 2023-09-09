package com.gnxcode.pitzza.service;

import com.gnxcode.pitzza.persitence.entity.PizzaEntity;
import com.gnxcode.pitzza.persitence.repository.PizzaPageSortRepository;
import com.gnxcode.pitzza.persitence.repository.PizzaRepository;
import com.gnxcode.pitzza.service.dto.UpdatePizzaPriceDto;
import com.gnxcode.pitzza.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    private  final PizzaRepository pizzaRepository;
    private final PizzaPageSortRepository pizzaPageSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPageSortRepository pizzaPageSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPageSortRepository = pizzaPageSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  pizzaPageSortRepository.findAll(pageable);
        //return this.pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAvailable(int page, int size, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return pizzaPageSortRepository.findByAvailableTrue(pageable);
        //return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getByName(String name){
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("The pizza not exits"));
    }

    public List<PizzaEntity> getByDescription(String description){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getByNotDescription(String description){
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getTop3PizzaLessPrice(Double price){
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public int countPizzaVegan(){
        return  pizzaRepository.countByVeganTrue();
    }

    public PizzaEntity getPizzaById(Integer idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public boolean existPizza(int idPizza){
        return pizzaRepository.existsById(idPizza);
    }

    public void deletePizza(int idPizza){
        pizzaRepository.deleteById(idPizza);
    }

    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePricePizza(UpdatePizzaPriceDto dto){
        pizzaRepository.updatePrice(dto);
        this.sendEmail();
    }

    private void sendEmail(){
        throw new EmailApiException();
    }
}
