package com.example.dev.repository;

/*
    created by Atiye Mousavi
    Date: 12/28/2022
    Time: 9:25 PM
*/


import com.example.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
