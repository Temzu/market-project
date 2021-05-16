package com.temzu.market_project.mscore.interfaces;

import java.util.Map;
import java.util.Optional;

public interface RedisRepository {
    Map<String, String> findAllTokens();
    void add(String type, String token);
    void delete(String id);
    String find(String id);
}
