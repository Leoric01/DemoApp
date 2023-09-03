package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserRequestDto;
import bank.mysuperbank_v1.models.DTOs.UserResponseDto;
import bank.mysuperbank_v1.models.ErrorResponse;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(), authorities);
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
        User user = new User(userRequestDto.getUsername(), userRequestDto.getFirstname(), userRequestDto.getLastname(), userRequestDto.getEmail(), userRequestDto.getPassword());
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
