package ru.grabovsky.authservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.authservice.models.dto.AuthResponse;
import ru.grabovsky.authservice.models.dto.RegisterRequest;
import ru.grabovsky.authservice.models.entity.User;
import ru.grabovsky.authservice.services.UserService;
import ru.grabovsky.authservice.utils.JwtTokenUtil;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        UserDetails userDetails = userService.register(user);
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
