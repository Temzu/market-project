package com.temzu.market_project.mscore.repositories;

import com.temzu.market_project.mscore.model.Movie;

import java.util.Map;

public interface RedisRepository {
    Map<Object, Object> findAllMovies();
    void add(Movie movie);
    void delete(String id);
    Movie findMovie(String id);
}
