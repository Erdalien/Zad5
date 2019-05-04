package com.pjwstk.springboot.repository;

import com.pjwstk.springboot.domain.Actors;
import com.pjwstk.springboot.domain.Films;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorsRepository {

    private static List<Actors> db = new ArrayList<Actors>();
    private static int currentId = 1;

    public List <Actors> getAll() {
        return db;
    }

    public void addActor(Actors actor){
        actor.setId(currentId++);
        db.add(actor);
    }

    public Actors getActor(int id){
        for (Actors actor : db) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }

    public void deleteActor(int id){
        db.remove(getActor(id));
    }

    public Actors updateActor(Actors a,Actors actor){
        a.setName(actor.getName());
        a.setSurname(actor.getSurname());
        a.setFilms(actor.getFilms());

        return a;
    }

    public void addFilm (Films film, Actors actor) {
        actor.getFilms().add(film);
    }

}
