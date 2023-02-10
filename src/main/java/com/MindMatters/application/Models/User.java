package com.MindMatters.application.Models;


import jakarta.persistence.*;

import java.util.List;

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

    @Column(nullable = false)
    private boolean isVerified; // is pending?

    public boolean isProvider() {
        return isProvider;
    }



    public User() {
    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.password = copy.password;
        this.isProvider = copy.isProvider;
        this.isVerified = copy.isVerified;
    }

    public User(String username, String password, boolean isProvider, boolean isVerified) {
        this.username = username;
        this.password = password;
        this.isProvider = isProvider;
        this.isVerified = isVerified;
    }

    public User(long id, String username, String password, boolean isProvider, boolean isVerified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isProvider = isProvider;
        this.isVerified = isVerified;
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

    public void setProvider(boolean provider) {
        isProvider = provider;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean verified) {
        isVerified = verified;
    }
}
