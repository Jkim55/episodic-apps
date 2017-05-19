package com.example.episodicshows.users;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        assert userService != null;
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createAUser(user);
    }
}
