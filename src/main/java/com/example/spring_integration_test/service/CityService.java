package com.example.spring_integration_test.service;

import com.example.spring_integration_test.model.City;
import com.example.spring_integration_test.repository.CityDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityService {
    private final CityDaoImpl cityDao;

    public int save(City city) {
        return cityDao.save(city);
    }

    public List<City> findAll() {
        return cityDao.findAll();
    }

    public City findById(int id) {
        return cityDao.findById(id);
    }

    public void delete(int id) {
        cityDao.delete(id);
    }

    public int update(int id, City city) {
        City cityOld = findById(id);
        cityOld.setName(city.getName());
        cityOld.setBadget(city.getBadget());
        cityOld.setSecretary(city.getSecretary());
        cityOld.setCount_peoples(city.getCount_peoples());
        return cityDao.update(id,cityOld);
    }

    public double findAvgDoublePeoples() {
        return cityDao.avgCountPeoples();
    }

    public List<Map<?, ?>> groupByNameAndSumCountPeoples() {
        return cityDao.groupByNameAndSumCountPeoples();
    }

    public int maxBudget() {
        return cityDao.maxBudget();
    }
}
