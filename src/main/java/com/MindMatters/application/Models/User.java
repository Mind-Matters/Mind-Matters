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

    @Column(nullable = false, length = 32)
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

    public User(String user_name, String password, boolean isProvider) {
        this.username = user_name;
        this.password = password;
        this.isProvider = isProvider;
    }

    public User(long id, String user_name, String password, boolean isProvider) {
        this.id = id;
        this.username = user_name;
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

    public void setUsername(String user_name) {
        this.username = user_name;
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
