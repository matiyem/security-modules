package com.example.dev.entity;

/*
    created by Atiye Mousavi
    Date: 12/28/2022
    Time: 9:23 PM
*/


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Integer id;

    @Column(name="user_name")
    private String name;

    @Column(name="user_passwd")
    private String password;

    @Column(name="user_email")
    private String email;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="roles",
            joinColumns = @JoinColumn(name="user_id")
    )
    @Column(name="user_role")
    private List<String> roles;

}
