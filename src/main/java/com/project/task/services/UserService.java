package com.project.task.services;

import com.project.task.models.User;
import com.project.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> save(User user) {
        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Missing request body field(s)");
        try {
            User savedUser = userRepository.save(user);
            // Check if the saved user has a valid ID (non-zero for long)
            if (savedUser.getId() != 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(savedUser);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User has not been created");
            }
        } catch (Exception e) {
            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error creating user: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findAll() {
        try {
            List<User> userList = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userList);
        } catch (Exception e) {
            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error fetching all users: " + e.getMessage());
        }
    }

    public ResponseEntity<?> findById(long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for id : " + id);
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(user);
            }
        } catch (Exception e) {
            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error fetching user by id: " + e.getMessage());
        }
    }

    public ResponseEntity<?> update(long id, User userDetails) {
        if (userDetails == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Missing request body field(s)");
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for id : " + id);
            }
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        } catch (Exception e) {
            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error updating user by id: " + e.getMessage());
        }
    }

    public ResponseEntity<String> deleteById(long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                if (userRepository.existsById(id))
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to delete the user.");
                else return ResponseEntity.status(HttpStatus.OK)
                        .body("User is deleted successfully for id : " + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found for id : " + id);
            }
        } catch (Exception e) {
            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Server error deleting user by id: " + e.getMessage());
        }
    }
}
