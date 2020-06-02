package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Event;
import com.example.repository.EventRepository;


@Service
@Transactional(readOnly = true)
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	
	public List<Event> findEvents() {
		return eventRepository.findAll();
	}

	public List<Event> findByStore(String store) {
		return eventRepository.findByStore(store);
	}


	public Page<Event> getEventList(Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page,9);
		return eventRepository.findAll(pageable);
	}
}
