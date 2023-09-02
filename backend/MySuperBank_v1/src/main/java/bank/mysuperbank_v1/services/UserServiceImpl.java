package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserDto;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(),userDto.getEmail(),userDto.getPassword());
        user.setRole("USER");
        return null;
    }
}
