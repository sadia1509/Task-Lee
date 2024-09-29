package com.project.task.controllers.api;

import com.project.task.models.User;
import com.project.task.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<?> addUser(@RequestBody(required = false) User user) {
        return userService.save(user);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody(required = false) User userDetails) {
        return userService.update(id, userDetails);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}
