package bank.mysuperbank_v1.security.authentication;

import bank.mysuperbank_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        return null;
    }

}
