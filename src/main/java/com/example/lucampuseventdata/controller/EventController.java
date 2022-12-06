package com.example.lucampuseventdata.controller;


import com.example.lucampuseventdata.model.Event;
import com.example.lucampuseventdata.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lucampus/event")
public class EventController {

    @Autowired
    private EventService service;

    @RequestMapping(value = "createEvent", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@Validated @RequestBody Event r) throws SQLException {
        service.saveEvent(r);
    }

    @GetMapping()
    public List<Event> fetchEventList() {
        return service.fetchEventList();
    }

    @GetMapping("findEvent/{id}")
    public ResponseEntity<Event> findEvent(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.findEvent(id), HttpStatus.OK);
    }

    @RequestMapping(value = "updateEvent/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> updateEvent(@RequestBody Event coffee, @PathVariable("id") int id) {
        Event cof = service.updateEvent(coffee, id);

        return new ResponseEntity<>(cof, HttpStatus.OK);
    }

}
