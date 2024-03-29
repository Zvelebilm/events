package com.example.events.models;

import com.example.events.DTO.EventCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
    private int maxPlayers;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
    private Long creatorId;

    @JsonIgnore
    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users_db = new ArrayList<>();

    public Event(String name, LocalDate date, String location, int maxPlayers, int price, String description, String imageUrl, String category) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Event(EventCreateDTO eventCreateDTO) {
        this.name = eventCreateDTO.name();
        this.date = eventCreateDTO.date();
        this.location = eventCreateDTO.location();
        this.maxPlayers = eventCreateDTO.maxPlayers();
        this.price = eventCreateDTO.price();
        this.description = eventCreateDTO.description();
        this.imageUrl = eventCreateDTO.imageUrl();
        this.category = eventCreateDTO.category();
    }

    public void setOwner(User user) {
        this.creatorId = user.getId();
    }

    public void addParticipant(User user) {
        this.users_db.add(user);
    }
}
