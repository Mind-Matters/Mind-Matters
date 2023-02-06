package com.MindMatters.application.models;
import jakarta.persistence.*;


@Entity
@Table(name ="roles")
public class Role {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

//    @Column
//    @OneToOne(mappedBy = "user")
//    private User user;



    @Column(nullable = false, unique = true)
    private String role_category;


    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public Role(long id, String role_category) {
        this.id = id;
        this.role_category = role_category;
    }

    public Role (){

    }

    public String getRole_category() {
        return role_category;
    }

    public void setRole_category(String role_category) {
        this.role_category = role_category;
    }
}
