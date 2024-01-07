package com.programming.userservice.auth;


import com.programming.userservice.config.JwtService;
import com.programming.userservice.models.Role;
import com.programming.userservice.models.User;
import com.programming.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.valueOf(request.getRole())).build();
            repository.save(user);
            var jwtToken =jwtService.generateToken(user);
        String role = user.getRole().name();
        return AuthenticationResponse.builder().token(jwtToken).role(role).userId(user.getUserId()).build();
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        String role = user.getRole().name();
        return AuthenticationResponse.builder().token(jwtToken).role(role).userId(user.getUserId()).build();
    }
}
