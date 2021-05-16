package com.temzu.market_project.mscore.services;

import com.temzu.market_project.mscore.interfaces.RedisRepository;
import com.temzu.market_project.mscore.interfaces.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public void putInvalidToken(String token) {
        redisRepository.add("INVALID", token);
    }

    @Override
    public boolean checkIfInvalid(String token) {
        return redisRepository.find(token).equals(token);
    }
}
