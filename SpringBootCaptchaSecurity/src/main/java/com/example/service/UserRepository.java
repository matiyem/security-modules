package com.example.service;

/*
    created by Atiye Mousavi
    Date: 1/3/2023
    Time: 9:03 PM
*/


import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}