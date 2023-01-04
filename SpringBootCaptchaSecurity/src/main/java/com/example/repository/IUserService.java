package com.example.repository;

/*
    created by Atiye Mousavi
    Date: 1/3/2023
    Time: 9:04 PM
*/


import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void createUser(User user);
    List<User> getAllUsers();
    Optional<User> getOneUser(Integer Id);
}