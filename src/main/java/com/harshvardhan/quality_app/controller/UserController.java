package com.harshvardhan.quality_app.controller;

import com.harshvardhan.quality_app.DTO.RegisterRequest;
import com.harshvardhan.quality_app.entity.User;
import com.harshvardhan.quality_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getallusers(){
        return userService.getallusers();
    }

    @PostMapping("/register")
    public User registeruser(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }


}
