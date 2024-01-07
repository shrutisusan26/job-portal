package com.programming.userservice.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/auth")
@CrossOrigin(origins = "http://localhost:3000/")

@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponse register (
            @RequestBody RegisterRequest request
    ){
        return authenticationService.register(request);
    };

    @PostMapping("/authentication")
    public AuthenticationResponse login (
            @RequestBody AuthenticationRequest request
    ){

        return authenticationService.login(request);

    };

}
