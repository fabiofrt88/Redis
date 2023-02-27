package com.example.redis.services;

import com.example.redis.entities.User;
import com.example.redis.entities.jpa.UserJPA;
import com.example.redis.entities.redis.UserRedis;
import com.example.redis.repositories.jpa.UserRepositoryJPA;
import com.example.redis.repositories.redis.UserRepositoryRedis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepositoryJPA userRepositoryJPA;
    private UserRepositoryRedis userRepositoryRedis;

    public UserRedis convertData(UserJPA user){
        UserRedis userRedis = new UserRedis();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public User create(UserJPA user) {
        if(user == null) return null;
        user.setId(null);
        return userRepositoryJPA.save(user);
    }

    public List<? extends User> readAll() {
        return userRepositoryJPA.findAll();
    }

    public User readOne(Long id) {
        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if (userRedis.isPresent()) {
            return userRedis.get();
        } else {
            UserJPA userFromDB = userRepositoryJPA.getById(id);
            userRepositoryRedis.save(convertData(userFromDB));
            return userFromDB;
        }
    }

    public User update(Long id, UserJPA user) {
        if(user == null) return null;
        user.setId(id);
        UserJPA newUser = userRepositoryJPA.save(user);

        Optional<UserRedis> userRedis = userRepositoryRedis.findById(id);
        if(userRedis.isPresent()){
            //userRepositoryRedis.deleteById(id);
            userRepositoryRedis.save(convertData(newUser));
        }
        return user;
    }

    public void delete(Long id) {

        userRepositoryJPA.deleteById(id);
        userRepositoryRedis.deleteById(id);
    }


}
