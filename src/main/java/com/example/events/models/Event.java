package com.example.events.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
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
    private Date date;
    private String location;
    private int maxPlayers;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
    private Long creatorId;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public Event(String name, Date date, String location, int maxPlayers, int price, String description, String imageUrl, String category) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.maxPlayers = maxPlayers;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;

    }

    public void setOwner(User user) {
        this.creatorId = user.getId();
    }

    public void addParticipant(User user) {
        this.users.add(user);
    }
}
