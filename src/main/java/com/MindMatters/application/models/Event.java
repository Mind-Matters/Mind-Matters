package com.MindMatters.application.models;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(nullable = false, length = 255)
    private String event;

    @Column(nullable = false)
    private Date date;

   @ManyToOne
    private User user;

    public Event(long id, String title, String event, Date date, User user) {
        this.id = id;
        this.title = title;
        this.event = event;
        this.date = date;
        this.user = user;
    }

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
