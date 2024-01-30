package com.example.events.DTO;

import lombok.Getter;

import java.time.LocalDate;

public record EventCreateDTO(String name, LocalDate date, String location, int maxPlayers, int price,
                             String description, String imageUrl, String category, String username) {

}
