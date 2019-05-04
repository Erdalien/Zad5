package com.pjwstk.springboot.repository;

import com.pjwstk.springboot.domain.Actors;
import com.pjwstk.springboot.domain.Comments;
import com.pjwstk.springboot.domain.Films;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmsRepository {

    private static List<Films> db = new ArrayList<>();
    private static int currentId = 1;
    private static int commentId = 1;

    public List <Films> getAll() {
        return db;
    }

    public void addFilm(Films film){
        film.setId(currentId++);
        db.add(film);
    }

    public Films getFilm(int id) {
        for(Films film : db) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }

    public void deleteFilm(int id) {
        db.remove(getFilm(id));
    }

    public Films updateFilm(Films f, Films film){
        f.setName(film.getName());
        return f;
    }

    public void addComment(Comments comment, Films film) {
        comment.setId(commentId++);
        film.getComments().add(comment);
    }

    public void deleteComments(int id, Films film) {
        for (Comments comment : film.getComments()) {
            if (comment.getId() == id) {
                film.getComments().remove(comment);
            }
        }
    }
    public void addActor (Films film, Actors actor) {
        film.getActors().add(actor);
    }
}
