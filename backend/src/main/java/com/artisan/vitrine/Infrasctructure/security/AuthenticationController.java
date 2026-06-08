package com.artisan.vitrine.Infrasctructure.security;

import com.artisan.vitrine.Infrasctructure.security.Jwt.AuthenticationRequest;
import com.artisan.vitrine.Infrasctructure.security.Jwt.AuthenticationResponse;
import com.artisan.vitrine.Infrasctructure.security.Jwt.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
