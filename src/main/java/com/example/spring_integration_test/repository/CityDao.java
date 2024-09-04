package com.example.spring_integration_test.repository;

import java.util.List;
import java.util.Map;

public interface CityDao<T,E extends Number> {
    int save(T t);

    List<T> findAll();

    T findById(E id);

    void delete(E id);

    int update(E id, T t);

    double avgCountPeoples();

    List<Map<?,?>> groupByNameAndSumCountPeoples();

    int maxBudget();

}
