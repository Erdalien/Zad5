package com.pjwstk.springboot.service;

import com.pjwstk.springboot.domain.Actors;
import com.pjwstk.springboot.domain.Comments;
import com.pjwstk.springboot.domain.Films;
import com.pjwstk.springboot.repository.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmsService {

    @Autowired
    FilmsRepository repository;

    public List<Films> getAll() {
        return repository.getAll();
    }

    public Films addFilm (Films film) {

        film.setActors(new ArrayList<>());
        film.setComments(new ArrayList<>());
        repository.addFilm(film);
        return film;
    }

    public Films getFilm(int id) {
        return repository.getFilm(id);
    }

    public Films updateFilm(Films f, Films film) {
        return repository.updateFilm(f, film);
    }

    public void deleteFilm (int id) {
        repository.deleteFilm(id);
    }

    public void addComment(Comments comment, Films film){
        repository.addComment(comment, film);
    }

    public void deleteComment(int id, Films film) {
        repository.deleteComments(id, film);
    }

    public void addActors(Films film, Actors actor) {
        repository.addActor(film, actor);
    }

    public void addRating(Films film, int rating) {
        double newRating = (film.getRate() * film.getCountRate() + rating) / (film.getCountRate() + 1);
        film.setCountRate(film.getCountRate() + 1);
        film.setRate(newRating);
    }
}
