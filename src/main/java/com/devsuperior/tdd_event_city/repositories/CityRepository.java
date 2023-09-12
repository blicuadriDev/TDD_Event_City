package com.devsuperior.tdd_event_city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.tdd_event_city.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
