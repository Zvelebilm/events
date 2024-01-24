package com.example.events.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Getter
    @Id
    @GeneratedValue
    private Long id;
    @NaturalId
    private String name;
    private String password;

    //todo - add roles
    //todo - add email etc


    @ManyToMany
    List<Event> events; //<-->


    public User(String username, String password) {
        this.name = username;
        this.password = password;
    }

    /*public void addToEvents(Event event) {
        this.events.add(event);
    }*/
}
