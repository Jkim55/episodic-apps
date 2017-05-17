package com.example.episodicshows.users;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User>  listAllUsers() {
        List<User> userList = new ArrayList<>();
        this.userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
}
