package com.example.events.DTO;


import com.example.events.models.Event;

import java.time.LocalDate;

public record EventDTO(String name, LocalDate date, String location, int maxPlayers, int price, String description,
    String imageUrl, String category
                       ) {

        public EventDTO(Event event) {
            this(event.getName(), event.getDate(), event.getLocation(), event.getMaxPlayers(), event.getPrice(),
                event.getDescription(), event.getImageUrl(), event.getCategory());
        }
}

