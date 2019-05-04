package com.pjwstk.springboot.controller;

import com.pjwstk.springboot.domain.Comments;
import com.pjwstk.springboot.domain.Films;
import com.pjwstk.springboot.service.FilmsService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodmovies")
public class FilmController {

    @Autowired
    FilmsService filmService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(filmService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addFilm(@RequestBody Films film) {
        return new ResponseEntity(filmService.addFilm(film), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getFilm(@PathVariable("id") int id) {
        Films film = filmService.getFilm(id);
        if (film == null) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        filmService.getFilm(id);
        return new ResponseEntity(filmService.getFilm(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public  ResponseEntity<?> deleteFilm(@PathVariable("id") int id) {
        Films film = filmService.getFilm(id);
        if (film == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        filmService.deleteFilm(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateFilm(@PathVariable("id") int id, @RequestBody Films film) {
        Films f = filmService.getFilm(id);
        if (f == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        filmService.updateFilm(f, film);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable("id") int id, @RequestBody Comments comment) {
        Films film = filmService.getFilm(id);
        if (film == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        filmService.addComment(comment, film);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable("id") int id) {
        Films film = filmService.getFilm(id);
        if (film == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(film.getComments(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{idF}/comments/{idK}")
    public ResponseEntity<?> deleteComments(@PathVariable("idF") int idF, @PathVariable("idK") int idK) {
        Films film = filmService.getFilm(idF);
        if (film == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        filmService.deleteComment(idK, film);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/allActors")
    public ResponseEntity<?> getActors(@PathVariable("id") int id) {
        Films film = filmService.getFilm(id);
        if (film == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(film.getActors(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/rate")
    public ResponseEntity<?> addRating(@PathVariable("id") int id, @RequestBody String json) throws JSONException {
        Films film = filmService.getFilm(id);
        if (film == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JSONObject jsonObject = new JSONObject(json);
        filmService.addRating(film, jsonObject.getInt("rate"));

        return new ResponseEntity(film, HttpStatus.OK);
    }
}
