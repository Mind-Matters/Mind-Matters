package com.MindMatters.application.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Table(name = "scaling")
public class Scaling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private User id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private long score;



}
