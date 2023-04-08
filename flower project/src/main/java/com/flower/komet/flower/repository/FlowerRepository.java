package com.flower.komet.flower.repository;

import com.flower.komet.flower.entity.FlowerEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FlowerRepository extends MongoRepository<FlowerEntity, String> {

    List<FlowerEntity> findAllByName(String name);

    @Query(value = "{'price': {$gte:?0}}", fields = "{'id':1,'name':1,'price':1}")
    List<FlowerEntity> findAllPriceMoreThan(Double precio);


    @Query(value = "{}", fields = "{'id':0,'name':1,'price':1}")
    List<FlowerEntity> orderFlowersByABC(Sort name);
}
