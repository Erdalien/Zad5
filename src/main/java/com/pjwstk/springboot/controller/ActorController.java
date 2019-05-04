package com.pjwstk.springboot.controller;

import com.pjwstk.springboot.domain.Actors;
import com.pjwstk.springboot.domain.Films;
import com.pjwstk.springboot.service.ActorsService;
import com.pjwstk.springboot.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodmovies/actors")
public class ActorController {

    @Autowired
    ActorsService aservice;
    @Autowired
    FilmsService fservice;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(aservice.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addActor(@RequestBody Actors actor) {
        return new ResponseEntity(aservice.addActor(actor), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getActor(@PathVariable("id") int id){
        Actors actor = aservice.getActor(id);
        if (actor == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aservice.getActor(id);
        return new ResponseEntity(aservice.getActor(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") int id){
        Actors actor = aservice.getActor(id);
        if (actor == null) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aservice.deleteActor(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateActor(@PathVariable("id") int id, @RequestBody Actors actor) {
        Actors a = aservice.getActor(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        aservice.updateActor(a, actor);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/film")
    public ResponseEntity<?> addFilm(@PathVariable("id") int id, @RequestBody Films filmm) {
        Actors actor = aservice.getActor(id);

        if (actor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aservice.addFilms(filmm, actor);
        Films film = fservice.getFilm(id);
        fservice.addActors(film, actor);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/film")
    public ResponseEntity<?> getFilm(@PathVariable("id") int id){
        Actors actor = aservice.getActor(id);
        if (actor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(actor.getFilms(), HttpStatus.OK);
    }
}
