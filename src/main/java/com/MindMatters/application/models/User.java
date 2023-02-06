package com.MindMatters.application.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true, length = 32)
    private String user_name;

    @Column(nullable = false, length = 32)
    private String password;

    @Column(nullable = false)
    @OneToOne
    private long role_id;

    public User() {
    }

    public User(String user_name, String password, long role_id) {
        this.user_name = user_name;
        this.password = password;
        this.role_id = role_id;
    }

    public User(long userId, String user_name, String password, long role_id) {
        this.userId = userId;
        this.user_name = user_name;
        this.password = password;
        this.role_id = role_id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }
}
