package com.myapp.controller;

import com.myapp.dto.LoginDTO;
import com.myapp.dto.LoginResponse;
import com.myapp.entity.User;
import com.myapp.security.JWTUtils;
import com.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());


        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user);
            System.out.println(token);
            LoginResponse loginResponse = new LoginResponse("Success",token);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        // Cek kredensial pengguna
//        User user = userRepository.findByUsername(username);
//        if (user == null || !password.equals(user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        // Generate token JWT
//        return JWTUtils.generateToken(user);
//    }
//}
//
