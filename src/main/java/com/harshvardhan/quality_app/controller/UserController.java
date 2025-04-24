package com.harshvardhan.quality_app.controller;

import com.harshvardhan.quality_app.DTO.RegisterRequest;
import com.harshvardhan.quality_app.DTO.UpdateUserDetails;
import com.harshvardhan.quality_app.entity.User;
import com.harshvardhan.quality_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService service){
        this.userService=service;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getallusers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registeruser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
    }

    @PutMapping("/users/{id}/roles")
    public ResponseEntity<User> updateUsersRole(@PathVariable Long id, @RequestBody Set<String> roles) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserRoles(id, roles));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id, @RequestBody UpdateUserDetails updateUserDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserDetails(id, updateUserDetails));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id ){
        userService.deleteUser(id);
        return ResponseEntity.ok("Mentioned user is now deleted from the DB");
    }


}
