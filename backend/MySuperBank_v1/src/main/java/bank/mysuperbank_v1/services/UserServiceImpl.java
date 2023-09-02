package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserDto;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(),userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return null;
    }
}
