package com.example.lucampuseventdata.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private int id;
    private String name;
    private String subject;
    private int slots;
    private int attending;
    private String roomNumber;


}
