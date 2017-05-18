package com.example.episodicshows.users;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createAUser(user);
    }
}
