package com.example.redis.controllers;

import com.example.redis.entities.User;
import com.example.redis.entities.jpa.UserJPA;
import com.example.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody UserJPA user){
        if(user == null) return null;
        return userService.create(user);
    }

    @GetMapping("/readall")
    public List<? extends User> readAll(){
        return userService.readAll();
    }

    @GetMapping("/{id}")
    public void readOne(@PathVariable Long id){
        userService.readOne(id);
    }

    @GetMapping("/{id}/fast")
    public void readOneFast(@PathVariable Long id){
        userService.readOne(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserJPA user){
        userService.update(id,user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);

    }


}
