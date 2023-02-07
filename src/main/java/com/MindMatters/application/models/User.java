package com.MindMatters.application.models;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 32)
    private String user_name;

    @Column(nullable = false, length = 32)
    private String password;

    @Column(nullable = false)
    private boolean isProvider;

    public User() {
    }

    public User(String user_name, String password, boolean isProvider) {
        this.user_name = user_name;
        this.password = password;
        this.isProvider = isProvider;
    }

    public User(long id, String user_name, String password, boolean isProvider) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.isProvider = isProvider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
