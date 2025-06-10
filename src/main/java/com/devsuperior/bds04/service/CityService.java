package com.devsuperior.bds04.service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> cities = repository.findAllByOrderByNameAsc();
        return cities.stream().map(x -> new CityDTO(x)).toList();
    }

    @Transactional
    public CityDTO insert (CityDTO dto) {
        City city = new City();
        copyDtoToEntity(dto, city);
        city = repository.save(city);
        return new CityDTO(city);
    }

    private void copyDtoToEntity(CityDTO dto, City city) {
        city.setName(dto.getName());
    }
}
