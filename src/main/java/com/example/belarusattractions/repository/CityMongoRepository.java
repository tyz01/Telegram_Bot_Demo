package com.example.belarusattractions.repository;

import com.example.belarusattractions.model.City;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface CityMongoRepository extends MongoRepository<City, String> {
    City findByName(String name);
}
