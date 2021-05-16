package com.temzu.market_project.mscore.repositories;

import com.temzu.market_project.mscore.exceptions.ItemNotFoundException;
import com.temzu.market_project.mscore.interfaces.RedisRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private RedisTemplate<String, String> redisTemplate;

    private HashOperations hashOperations;

    public RedisRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, String> findAllTokens() {
        return null;
    }

    @Override
    public void add(String type, String token) {
        hashOperations.put("INVALID", token, token);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public String find(String id) {
        Object o = hashOperations.get("INVALID", id);
        if (o != null) {
            return (String) o;
        }
        throw new ItemNotFoundException("Authorization token not found");
    }
}
