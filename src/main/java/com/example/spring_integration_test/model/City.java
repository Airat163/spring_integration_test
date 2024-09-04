package com.example.spring_integration_test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class City {
    private int id;
    private String name;

    private Long count_peoples;
    private Long badget;
    private String secretary;

    public City(String name, Long count_peoples, Long badget, String secretary) {
        this.name = name;
        this.count_peoples = count_peoples;
        this.badget = badget;
        this.secretary = secretary;
    }
}
