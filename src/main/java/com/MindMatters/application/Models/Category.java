package com.MindMatters.application.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            mappedBy = "categories")
    @JsonIgnore
    private Set<Event> events = new HashSet<>();

    @Column(nullable = false, length = 32)
    private String category;

    public Category(){

    }

    public Category(long id, String category) {
        this.id = id;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
