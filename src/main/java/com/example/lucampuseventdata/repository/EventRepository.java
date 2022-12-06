package com.example.lucampuseventdata.repository;

import com.example.lucampuseventdata.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private Connection con = null;
    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;


    private Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }

    public void saveEvent(Event event) throws SQLException {
        String create = "INSERT INTO event (id, name, subject, slots, attending, roomNumber) " +
                " VALUES (?, ?, ?, ?, ?, ?)";

        System.out.println(create);
        PreparedStatement stm = getDBConnection().prepareStatement(create);

        stm.setLong(1, event.getId());
        stm.setString(2, event.getName());
        stm.setString(3, event.getSubject());
        stm.setInt(4, event.getSlots());
        stm.setInt(5, event.getAttending());
        stm.setString(6, event.getRoomNumber());
        stm.executeUpdate();
    }

    public List<Event> fetchAllEvents() {
        List<Event> eventList = new ArrayList<>();
        Event event;
        String selectAll = "SELECT * FROM event";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setSubject(rs.getString("subject"));
                event.setSlots(rs.getInt("slots"));
                event.setAttending(rs.getInt("attending"));
                event.setRoomNumber(rs.getString("roomNumber"));
                eventList.add(event);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eventList;
    }

    public Optional<Event> findEvent(int id) {
        Optional<Event> ev = Optional.empty();
        String find = "SELECT * FROM event WHERE id = '" + id + "'";
        Event event = new Event();

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(find);

            while (rs.next()) {
                event.setId(rs.getInt("id"));
                event.setName(rs.getString("name"));
                event.setSubject(rs.getString("subject"));
                event.setSlots(rs.getInt("slots"));
                event.setAttending(rs.getInt("attending"));
                event.setRoomNumber(rs.getString("roomNumber"));

                ev = Optional.of(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ev;
    }

    public Optional<Event> updateEvent(Event event, int id) {
        Optional<Event> ev = findEvent(id);

        String update = "UPDATE event SET id = ?, name = ?, subject = ?, slots = ?, attending = ?, roomNumber = ? WHERE id = ?";

        if (!ev.isPresent()) {
            ev = Optional.empty();
            return ev;
        }

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(update);
            stm.setInt(1, id);
            stm.setString(2, event.getName());
            stm.setString(3, event.getSubject());
            stm.setInt(4, event.getSlots());
            stm.setInt(5, event.getAttending());
            stm.setString(6, event.getRoomNumber());
            stm.setLong(7, id);

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ev;
    }
}
