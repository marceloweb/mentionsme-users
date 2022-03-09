package com.mesavirtual.mentionsmeusers.controller;

import com.mesavirtual.mentionsmeusers.model.User;
import com.mesavirtual.mentionsmeusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String init() {
        return "API Users";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
