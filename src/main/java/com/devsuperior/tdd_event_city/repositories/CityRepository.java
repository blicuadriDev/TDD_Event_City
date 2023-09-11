package com.devsuperior.tdd_event_city.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.tdd_event_city.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
