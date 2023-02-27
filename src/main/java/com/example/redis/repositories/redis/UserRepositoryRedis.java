package com.example.redis.repositories.redis;

import com.example.redis.entities.redis.UserRedis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryRedis extends JpaRepository<UserRedis, Long> {
}
