package com.MindMatters.application.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "scaling_data")
public class ScalingData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    private User user;

    @Column(nullable = false)
    private long score;

    @Column(nullable = false)
    private Date date;

    public ScalingData() {
    }

    public ScalingData(long id, User user, long score, Date date) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.date = date;
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

    public void setUser(User user_id) {
        this.user = user_id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
