package com.mesavirtual.mentionsmeusers.controller;

import com.mesavirtual.mentionsmeusers.model.User;
import com.mesavirtual.mentionsmeusers.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody String data) {

        JSONObject all = new JSONObject(data);
        String firstName = all.getString("first_name");
        String lastName = all.getString("last_name");
        String email = all.getString("email");

        DateTimeFormatter createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCreatedAt(createdAt.format(now));

        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody String data) {

        User user = userRepository
                        .findById(userId).orElseThrow();

        DateTimeFormatter createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        JSONObject all = new JSONObject(data);
        String firstName = all.getString("first_name");
        String lastName = all.getString("last_name");
        String email = all.getString("email");

        user.setEmail(email);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository
                        .findById(userId)
                        .orElseThrow();

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
