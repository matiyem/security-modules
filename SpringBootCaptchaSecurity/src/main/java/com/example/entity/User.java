package com.example.entity;

/*
    created by Atiye Mousavi
    Date: 1/3/2023
    Time: 9:02 PM
*/


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;

    @Transient
    private String captcha;

    @Transient
    private String hiddenCaptcha;

    @Transient
    private String realCaptcha;
}