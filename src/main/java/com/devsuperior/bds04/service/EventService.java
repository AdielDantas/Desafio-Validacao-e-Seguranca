package com.devsuperior.bds04.service;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return events.map(x -> new EventDTO(x));
    }

    @Transactional
    public EventDTO insert (EventDTO dto) {
        Event event = new Event();
        copyDtoToEntity(dto, event);
        event = repository.save(event);
        return new EventDTO(event);
    }

    private void copyDtoToEntity(EventDTO dto, Event event) {
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(cityRepository.getReferenceById(dto.getCityId()));
    }
}
