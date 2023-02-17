package com.MindMatters.application.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="track_medication")
public class TrackMedication {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean taken;

    public TrackMedication() {
    }

    public TrackMedication(User user, Date date, Boolean taken) {
        this.user = user;
        this.date = date;
        this.taken = taken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }
}
