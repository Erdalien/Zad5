package com.pjwstk.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Actors {

    private int id;
    private String Name;
    private String Surname;
    @JsonIgnore
    private List<Films> films;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) {
        this.films = films;
    }
}
