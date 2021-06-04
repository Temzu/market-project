package com.temzu.market_project.mscore.interfaces;

public interface RedisService {

    void putInvalidToken(String token);

    boolean checkIfInvalid(String token);
}
