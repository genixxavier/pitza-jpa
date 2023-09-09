package com.gnxcode.pitzza.web.controller;

import com.gnxcode.pitzza.persitence.entity.PizzaEntity;
import com.gnxcode.pitzza.service.PizzaService;
import com.gnxcode.pitzza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, size));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int idPizza) {
        return ResponseEntity.ok(pizzaService.getPizzaById(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "price") String sort,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(pizzaService.getAvailable(page, size, sort, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable String description){
        return ResponseEntity.ok(pizzaService.getByDescription(description));
    }

    @GetMapping("/not-description/{description}")
    public ResponseEntity<List<PizzaEntity>> getByNotDescription(@PathVariable String description){
        return ResponseEntity.ok(pizzaService.getByNotDescription(description));
    }

    @GetMapping("count-vegan")
    public ResponseEntity<Integer> countPizzaVegan(){
        return ResponseEntity.ok(pizzaService.countPizzaVegan());
    }

    @GetMapping("/top-price-less/{price}")
    public ResponseEntity<List<PizzaEntity>> getTop3PizzaLessPrice(@PathVariable Double price){
        return ResponseEntity.ok(pizzaService.getTop3PizzaLessPrice(price));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() == null || !pizzaService.existPizza(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && pizzaService.existPizza(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePricePizza(@RequestBody UpdatePizzaPriceDto dto){
        if(pizzaService.existPizza(dto.getPizzaId())){
            pizzaService.updatePricePizza(dto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza){
        if(pizzaService.existPizza(idPizza)){
            pizzaService.deletePizza(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
