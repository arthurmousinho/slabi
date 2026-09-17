package com.slabi.backend.user.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.slabi.backend.user.entity.User;
import com.slabi.backend.user.service.UserService;
import com.slabi.backend.user.dto.CreateUserRequest;
import com.slabi.backend.user.dto.UserResponse;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Lista os usuários cadastrados")
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users.stream().map(UserResponse::from).toList());
    }

    @PostMapping
    @Operation(summary = "Cria um usuário")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody CreateUserRequest request) {
        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(savedUser));
    }
}
