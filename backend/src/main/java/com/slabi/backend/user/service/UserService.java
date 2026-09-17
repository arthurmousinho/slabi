package com.slabi.backend.user.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.slabi.backend.user.entity.User;
import com.slabi.backend.user.repository.UserRepository;
import com.slabi.backend.user.dto.CreateUserRequest;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(CreateUserRequest request) {
        User user = new User(request.name(), request.email(), request.passwordHash(), request.role());
        return userRepository.save(user);
    }
}
