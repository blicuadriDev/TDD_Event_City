package com.devsuperior.tdd_event_city.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.tdd_event_city.dto.EventDTO;
import com.devsuperior.tdd_event_city.entities.City;
import com.devsuperior.tdd_event_city.entities.Event;
import com.devsuperior.tdd_event_city.repositories.EventRepository;
import com.devsuperior.tdd_event_city.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = repository.getReferenceById(id);
			entity.setDate(dto.getDate());
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity.setId(dto.getId());
			entity.setCity(new City(dto.getCityId(), null));

			entity = repository.save(entity);

			return new EventDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Event ID " + id + " Not Found");
		}
	}

}
