package com.example.spring_integration_test.controller;

import com.example.spring_integration_test.model.City;
import com.example.spring_integration_test.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CityController {
    private final CityService cityService;

    @PostMapping("/city")
    public ResponseEntity<String> save(@RequestBody City city) {
        int save = cityService.save(city);
        return new ResponseEntity<>("пользователь сохранен успешно", HttpStatus.CREATED);
    }

    @GetMapping("/cities")
    public List<City> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/city/{id}")
    public City findById(@PathVariable int id) {
        return cityService.findById(id);
    }


    @DeleteMapping("/city/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        cityService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody City city) {
        int update = cityService.update(id, city);
        log.info("City обновлен =" + update);
        return new ResponseEntity<>("пользователь успешно обновлён!" + id, HttpStatus.ACCEPTED);
    }

    @GetMapping("/city/avg")
    public double findAvgDoublePeoples() {
        return cityService.findAvgDoublePeoples();
    }

    @GetMapping("/city/group")
    public List<Map<?, ?>> groupByNameAndSumCountPeoples() {
        return cityService.groupByNameAndSumCountPeoples();
    }

    @GetMapping("/city/max_budget")
    public ResponseEntity<String> maxBudget() {
        return new ResponseEntity<>("максимальный бюджет = " + cityService.maxBudget(),
                HttpStatus.OK);
    }
}
