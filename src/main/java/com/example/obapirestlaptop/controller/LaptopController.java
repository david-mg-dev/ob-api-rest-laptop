package com.example.obapirestlaptop.controller;

import com.example.obapirestlaptop.entities.Laptop;
import com.example.obapirestlaptop.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    /**
     * Devolver listado todos los laptop
     * http://localhost/api/laptop
     * @return
     */
    @GetMapping("/api/laptop")
    public List<Laptop> findALl(){

        return laptopRepository.findAll();
    }

    /**
     * Buscar laptop por id
     * http://localhost/api/laptop
     * @param id
     * @return
     */
    @GetMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){

        Optional<Laptop> laptopOptional = laptopRepository.findById(id);

        if(laptopOptional.isPresent())
            return ResponseEntity.ok(laptopOptional.get());
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * Crear laptop Base datos
     * http://localhost/api/laptop
     * @param laptop
     * @return
     */
    @PostMapping("/api/laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Actualiza laptop existente
     * http://localhost/api/laptop
     * @param laptop
     * @return
     */
    @PutMapping("/api/laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if(laptop.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())) {
            return ResponseEntity.notFound().build();
        }
        // Actualizamos laptop
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Borra laptop por id
     * http://localhost/api/laptop/id
     * @param id
     * @return
     */
    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Borra todos los laptop
     * http://localhost/api/laptop
     * @return
     */
    @DeleteMapping("/api/laptop")
    public ResponseEntity<Laptop> deleteAll(){

        laptopRepository.deleteAll();

        return ResponseEntity.noContent().build();
    }


}
