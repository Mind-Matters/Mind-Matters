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

//    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(String user_name, String password, Role role) {
        this.user_name = user_name;
        this.password = password;
        this.role = role;
    }

    public User(long id, String user_name, String password, Role role) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
