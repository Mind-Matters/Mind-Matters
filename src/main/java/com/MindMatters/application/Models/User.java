package com.MindMatters.application.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 32)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isProvider;

    public User() {
    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.password = copy.password;
        this.isProvider = copy.isProvider;
    }

    public User(String username, String password, boolean isProvider) {
        this.username = username;
        this.password = password;
        this.isProvider = isProvider;
    }

    public User(long id, String username, String password, boolean isProvider) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isProvider = isProvider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsProvider() {
        return isProvider;
    }

    public void setIsProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }
}
