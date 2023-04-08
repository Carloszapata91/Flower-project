package com.flower.komet.flower.controller;

import com.flower.komet.flower.entity.FlowerEntity;
import com.flower.komet.flower.repository.FlowerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/flower")
@Slf4j
public class FlowerRestController {

    @Autowired
    private FlowerRepository flowerRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody FlowerEntity input){

        FlowerEntity save = flowerRepository.save(input);
        log.info("Creando una flor: {}",save.getName());
        return ResponseEntity.ok(save);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<FlowerEntity> findAllFlowers(){
        log.info("requesting all flores ");

        return flowerRepository.findAll();
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlower(FlowerEntity flowerEntity){
        log.info("Borrando una flor por ID");
        flowerRepository.delete(flowerEntity);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<FlowerEntity> findFlowers(@PathVariable String id){
        log.info("Retornando flor por ID");

        return flowerRepository.findById(id);
    }

    @PostMapping("/flores-batch")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<FlowerEntity> deleteAllAndSaveNewBatch(@RequestBody List<FlowerEntity> flores) {
        flowerRepository.deleteAll();
        log.info("Creando un batch de flores");
        return flowerRepository.saveAll(flores);
    }

    @GetMapping("/getByName/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<FlowerEntity> findAllFlowersByName(@PathVariable String nombre){
        log.info("Requesting all flowers by name");
        return flowerRepository.findAllByName(nombre);
    }

    @GetMapping("/getFlowersPriceMoreThan/{precio}")
    @ResponseStatus(HttpStatus.OK)
    public List<FlowerEntity> findAllFlowersPriceMoreThan(@PathVariable Double precio){
        log.info("Requesting flowers with price more than: {}",precio);
        return flowerRepository.findAllPriceMoreThan(precio);
    }

    @GetMapping("/getOrderedFlowers")
    @ResponseStatus (HttpStatus.OK)
    public List<FlowerEntity> getOrderedFlowers (){
        log.info("Requesting all flowers ordered by name");
        return flowerRepository.orderFlowersByABC(Sort.by(Sort.Direction.ASC,"name"));
    }

}
