package com.example.spring_integration_test.controller;


import com.example.spring_integration_test.model.City;
import com.example.spring_integration_test.repository.CityDaoImpl;
import com.example.spring_integration_test.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityDaoImpl cityDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveCity() throws Exception {
        City city = new City("Samara", 1_200_000L, 200L,
                "Azarov");
        mockMvc.perform(post("/api/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(city)))
                .andExpect(status().isCreated())
                .andExpect(content().string("пользователь сохранен успешно"));
    }

    @Test
    void shouldReturnAListOfCities() throws Exception {
        List<City> cityList = cityDao.findAll();
        mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cityList)))
                .andExpect(jsonPath("$[0].name").value("Samara"));
    }

    @Test
    void shouldFindById() throws Exception {
        int id = 4;
        City city = cityService.findById(id);
        mockMvc.perform(get("/api/city/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(city)));
    }

    @Test
    void shouldDeleteById() throws Exception {
        int id = 5;
        mockMvc.perform(delete("/api/city/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateCityById() throws Exception {
        int id = 4;
        City city = new City("Moscow", 20_000_000L, 10200L,
                "Sobyanin");
        mockMvc.perform(put("/api/city/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(city)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("пользователь успешно обновлён!" + id));
    }

    @Test
    void shouldAvgCountPeoples() throws Exception {
        Double avg = cityService.findAvgDoublePeoples();
        mockMvc.perform(get("/api/city/avg"))
                .andExpect(status().isOk())
                .andExpect(content().string(avg.toString()));
    }

    @Test
    void shouldMaxBudget() throws Exception {
        int maxBudget = cityService.maxBudget();
        mockMvc.perform(get("/api/city/max_budget"))
                .andExpect(status().isOk())
                .andExpect(content().string("максимальный бюджет = " + maxBudget));
    }


}