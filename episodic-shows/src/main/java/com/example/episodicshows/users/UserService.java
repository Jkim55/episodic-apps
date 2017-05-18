package com.example.episodicshows.users;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    public User createAUser(User user) {
        return userRepository.save(user);

    }

}
