package com.example;

/*
    created by Atiye Mousavi
    Date: 12/30/2022
    Time: 6:59 PM
*/


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encoder {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
