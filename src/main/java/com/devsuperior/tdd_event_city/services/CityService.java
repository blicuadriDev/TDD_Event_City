package com.devsuperior.tdd_event_city.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.tdd_event_city.dto.CityDTO;
import com.devsuperior.tdd_event_city.entities.City;
import com.devsuperior.tdd_event_city.repositories.CityRepository;
import com.devsuperior.tdd_event_city.services.exceptions.DatabaseException;
import com.devsuperior.tdd_event_city.services.exceptions.ResourceNotFoundException;


@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> result = repository.findAll(Sort.by("name"));
		return result.stream().map(x-> new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		entity.setName(dto.getName());
		
		entity = repository.save(entity);
		
		return new CityDTO(entity);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Cidade não encontrado");
		}
		try {
	        	repository.deleteById(id);    		
		}
	    	catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException("Falha de integridade referencial");
	   	}
	}
	
	
	}
