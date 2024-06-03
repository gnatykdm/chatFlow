package com.connect.connect.controller;

import com.connect.connect.model.entity.User;
import com.connect.connect.model.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-management")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        logger.info("Updating user: {}", user);
        try {
            userService.updateUser(user);
            return ResponseEntity.ok("User updated");
        } catch (Exception e) {
            logger.error("Error updating user: ", e);
            return ResponseEntity.status(500).body("Error updating user");
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable Integer userId) {
        logger.info("Deleting user with ID: {}", userId);
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e) {
            logger.error("Error deleting user: ", e);
            return ResponseEntity.status(500).body("Error deleting user");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        logger.info("Getting user with ID: {}", userId);
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Getting all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/find")
    public ResponseEntity<User> login(@RequestParam String username) {
        logger.info("Finding user by username: {}", username);
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
