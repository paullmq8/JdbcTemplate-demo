package com.example.jdbctemplatedemo.controller;

import com.example.jdbctemplatedemo.dto.UserCreationDTO;
import com.example.jdbctemplatedemo.dto.UserUpdateDTO;
import com.example.jdbctemplatedemo.pojo.User;
import com.example.jdbctemplatedemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user1")
    public User createUser(@Valid @RequestBody UserCreationDTO dto,
                           HttpServletResponse response) throws IOException {
        User user = dto.convertToUser();
        int count = userService.addUser1(user);
        if (count == 1) {
            response.setStatus(HttpStatus.CREATED.value());
            return user;
        } else {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Could not create this user.");
            return null;
        }
    }

    @PostMapping("/user2")
    public User createUser2(@Valid @RequestBody UserCreationDTO dto,
                           HttpServletResponse response) throws IOException {
        User user = dto.convertToUser();
        int count = userService.addUser2(user);
        if (count == 1) {
            response.setStatus(HttpStatus.CREATED.value());
            return user;
        } else {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Could not create this user.");
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id,
                               HttpServletResponse response) throws IOException {
        int count = userService.deleteUserById(id);
        if (count != 1) {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Could not delete this user.");
        }
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id,
                           @Valid @RequestBody UserUpdateDTO dto,
                           HttpServletResponse response) throws IOException {
        User user = dto.convertToUser();
        user.setId(id);
        int count = userService.updateUser(user);
        if (count != 1) {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Could not update this user.");
            return null;
        }
        return user;
    }

    @GetMapping("/all1")
    public List<User> getAllUser1() {
        return userService.getAllUsers1();
    }

    @GetMapping("/all2")
    public List<User> getAllUser2() {
        return userService.getAllUsers2();
    }
}
