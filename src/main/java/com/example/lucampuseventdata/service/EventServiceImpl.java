package com.example.lucampuseventdata.service;

import com.example.lucampuseventdata.model.Event;
import com.example.lucampuseventdata.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repo;


    @Override
    public void saveEvent(Event event) throws SQLException {
        repo.saveEvent(event);
    }

    @Override
    public List<Event> fetchEventList() {
        return repo.fetchAllEvents();
    }

    @Override
    public Event updateEvent(Event event, int id) {
        return repo.updateEvent(event, id).get();
    }

    @Override
    public Event findEvent(int id) {
        return repo.findEvent(id).get();
    }
}
