package com.example.events.repositories;

import com.example.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
}
