package com.MindMatters.application.models;


import jakarta.persistence.*;

@Entity
@Table(name =  "relations")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



}
