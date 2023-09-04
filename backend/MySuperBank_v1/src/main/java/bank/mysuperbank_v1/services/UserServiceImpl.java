package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserRequestDto;
import bank.mysuperbank_v1.models.DTOs.UserResponseDto;
import bank.mysuperbank_v1.models.ErrorResponse;
import bank.mysuperbank_v1.models.Role;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.RoleRepository;
import bank.mysuperbank_v1.repositories.UserRepository;
import bank.mysuperbank_v1.security.authentication.AuthenticationRequest;
import bank.mysuperbank_v1.security.authentication.AuthenticationResponse;
import bank.mysuperbank_v1.security.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public ResponseEntity<?> login(AuthenticationRequest loginDetails){
        String username = loginDetails.getUsername();
        String password = loginDetails.getPassword();

        if (username == null || username.isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Username is required"));
        }
        if (username.length() < 4) {
            return ResponseEntity.status(400).body(new ErrorResponse("Invalid username"));
        }
        if (password == null || password.isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Password is required"));
        }
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.status(400).body(new ErrorResponse("Unregistered name"));
        }
        User user = userRepository.findUserByUsername(username);
        if (!verifyUser(username, password)) {
            return ResponseEntity.status(400).body(new ErrorResponse("Invalid credentials"));
        }
        return ResponseEntity.status(200).body(new AuthenticationResponse(generateToken(loginDetails.getUsername(), loginDetails.getPassword())));
    }



    @Override
    public boolean verifyUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
        return new BCryptPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    public String generateToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateToken(authentication);
    }


    @Override
    public ResponseEntity<?> registerUser(UserRequestDto userRequestDto) {
        if (userRequestDto.getUsername() == null || userRequestDto.getUsername().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Username is required"));
        }
        if (userRequestDto.getUsername().length() < 4) {
            return ResponseEntity.status(400).body(new ErrorResponse("Username must be at least 4 characters long"));
        }
        if (userRequestDto.getFirstname() == null || userRequestDto.getFirstname().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("First name is required"));
        }
        if (userRequestDto.getLastname() == null || userRequestDto.getLastname().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Last name is required"));
        }

        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Email is required"));
        }

        if (userRequestDto.getPassword() == null || userRequestDto.getPassword().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Password is required"));
        }

        if (userRequestDto.getPassword().length() < 8) {
            return ResponseEntity.status(400).body(new ErrorResponse("Password must be at least 8 characters long"));
        }
        if (userRequestDto.getMatchingPassword() == null || userRequestDto.getMatchingPassword().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Confirm password is required"));
        }

        if (userRequestDto.getMatchingPassword().length() < 8) {
            return ResponseEntity.status(400).body(new ErrorResponse("Confirm password must be at least 8 characters long"));
        }

        if (!userRequestDto.getMatchingPassword().equals(userRequestDto.getPassword())){
            return ResponseEntity.status(400).body(new ErrorResponse("Passwords do not match"));
        }

        if (verifySameName(userRequestDto.getUsername())) {
            return ResponseEntity.status(409).body(new ErrorResponse("Username is already taken"));
        }
        if (!isEmailValid(userRequestDto.getEmail())) {
            return ResponseEntity.status(400).body(new ErrorResponse("Invalid email"));
        }

        if (verifySameEmail(userRequestDto.getEmail())) {
            return ResponseEntity.status(409).body(new ErrorResponse("Email already exists"));
        }
        User user = new User(userRequestDto.getUsername(), userRequestDto.getFirstname(), userRequestDto.getLastname(), userRequestDto.getEmail(), passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRole(new Role("USER"));
        userRepository.save(user);
        UserResponseDto userResponseDTO = new UserResponseDto();
        userResponseDTO.setId(userRepository.findUserByEmail(user.getEmail()).getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());

        return ResponseEntity.status(201).body(userResponseDTO);
    }

    @Override
    public List<UserResponseDto> getAllUserResponseDto() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserResponseDto userResponseDto = new UserResponseDto();
                    userResponseDto.setId(user.getId());
                    userResponseDto.setUsername(user.getUsername());
                    userResponseDto.setFirstName(user.getFirstName());
                    userResponseDto.setLastName(user.getLastName());
                    userResponseDto.setEmail(user.getEmail());
                    return userResponseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean verifySameName(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean verifySameEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public boolean isEmailValid(String email) {
        return Pattern.compile("^(.+)@(\\S+)$")
                .matcher(email)
                .matches();
    }
}
