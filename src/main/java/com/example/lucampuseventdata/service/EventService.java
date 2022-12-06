package com.example.lucampuseventdata.service;

import com.example.lucampuseventdata.model.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventService {
    void saveEvent(Event event) throws SQLException;

    List<Event> fetchEventList();

    Event updateEvent(Event coffee, int id);

    Event findEvent(int id);
}
