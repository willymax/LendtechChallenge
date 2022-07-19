package com.william.lendtech.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 * Email: william.k.makau@gmail.com
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImplementation userServiceImplementation;


    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userServiceImplementation.saveUser(user));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<User> getUserProfile() {
        return ResponseEntity.ok().body(userServiceImplementation.getUserProfile());
    }
    @GetMapping("/user/balance")
    public ResponseEntity<Float> getUserBalance() {
        return ResponseEntity.ok().body(userServiceImplementation.getCurrentBalance());
    }
}
