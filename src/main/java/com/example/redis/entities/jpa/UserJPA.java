package com.example.redis.entities.jpa;


import com.example.redis.entities.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserJPA extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

}