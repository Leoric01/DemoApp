package bank.mysuperbank_v1.security.authentication;

import bank.mysuperbank_v1.repositories.UserRepository;
import bank.mysuperbank_v1.security.config.JwtService;
import bank.mysuperbank_v1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    @Autowired
    public AuthenticationService(UserRepository repository, UserService userService, PasswordEncoder encoder, JwtService jwtService, AuthenticationManager manager) {
        this.repository = repository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.manager = manager;
    }

}
