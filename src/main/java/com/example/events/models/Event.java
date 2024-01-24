package com.example.events.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToMany
    private List<User> users;

}
