package com.example.service;

/*
    created by Atiye Mousavi
    Date: 1/3/2023
    Time: 9:06 PM
*/


import com.example.entity.User;
import com.example.repository.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repo;

    @Override
    public void createUser(User user) {

        repo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Optional<User> getOneUser(Integer id) {
        return repo.findById(id);
    }

}