package com.MindMatters.application.models;


import jakarta.persistence.*;

@Entity
@Table(name =  "relations")
public class Relation {

    /* how 'relations' is related to 'users'
    * A provider can have multiple patients
    * UserId(provider from users table) -(one to many)> relations
    * Users are designated as a provider if their 'role' column = 2
    *
    * ptId(in relations table) -(OneToOne)> user(user table)*/

    @Id //this is the provider id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private User user_id;



/*
* provider ID | pt ID
* providerId = 2 | ptId = 1
* providerId = 2 | ptId = 3*/

}
