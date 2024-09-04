package com.example.spring_integration_test.repository;

import com.example.spring_integration_test.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityDaoImpl implements CityDao<City,Integer> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int save(City city) {
        return jdbcTemplate.update("insert into city(name,count_peoples,badget,secretary) " +
                        "VALUES (?,?,?,?)",
                city.getName(),
                city.getCount_peoples(),
                city.getBadget(),
                city.getSecretary());
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query("select * from city",
                new BeanPropertyRowMapper<>(City.class));
    }

    /*@Override
    public City findById(int id) {
        return jdbcTemplate.queryForObject("select * from city where id= ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(City.class));
    }*/

    @Override
    public City findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from city where id= ?",
                new Object[]{id}, (rs, rowNum) ->
                        new City(rs.getString(2),
                                rs.getLong(3),
                                rs.getLong(4),
                                rs.getString(5)));
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("delete from city WHERE id = ?", id);
    }

    @Override
    public int update(Integer id, City city) {
        return jdbcTemplate.update("update city SET name = ?, count_peoples = ?, " +
                        "badget = ?, secretary = ? where id = ?",
                city.getName(),
                city.getCount_peoples(),
                city.getBadget(),
                city.getSecretary(),
                id);
    }

    @Override
    public double avgCountPeoples() {
        return jdbcTemplate.queryForObject("select avg(count_peoples) from city", Double.class);
    }

    @Override
    public List<Map<?, ?>> groupByNameAndSumCountPeoples() {
        List<Map<?, ?>> mapList = new ArrayList<>();

        jdbcTemplate.query("select name,sum(count_peoples) from city group by name",
                rs ->
                {
                    mapList.add(Map.of(rs.getString(1), rs.getInt(2)));
                });
        return mapList;
    }

    @Override
    public int maxBudget() {
        return jdbcTemplate.queryForObject("select max(badget) from city",
                Integer.class);
    }
}
