package com.temzu.market_project.mscore.entities;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("TokenInfo")
public class TokenInfo implements Serializable {
    private String id;
    private String token;
    private int ttl;

    public TokenInfo(String token, int ttl) {
        this.ttl = ttl;
        this.token = token;
    }
}
