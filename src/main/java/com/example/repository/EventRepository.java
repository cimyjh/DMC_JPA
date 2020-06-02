package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
	
	@Query("select e from Event e where e.store = :store")
	List<Event> findByStore(@Param("store") String store);

}
