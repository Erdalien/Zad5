package com.pjwstk.springboot.service;

import com.pjwstk.springboot.domain.Actors;
import com.pjwstk.springboot.domain.Films;
import com.pjwstk.springboot.repository.ActorsRepository;
import com.pjwstk.springboot.repository.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorsService {

    @Autowired
    ActorsRepository repository;

    public List<Actors> getAll() {
        return repository.getAll();
    }
    public Actors addActor(Actors actor){
        actor.setFilms(new ArrayList<Films>());
        repository.addActor(actor);
        return actor;
    }
    public Actors getActor(int id){
        return repository.getActor(id);
    }

    public void deleteActor(int id){
        repository.deleteActor(id);
    }

    public Actors updateActor(Actors a, Actors actor){
        return repository.updateActor(a, actor);
    }

    public void addFilms(Films film, Actors actor){
        repository.addFilm(film, actor);
    }


}
