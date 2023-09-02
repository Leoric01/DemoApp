package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserDto;
import bank.mysuperbank_v1.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(UserDto userDto);
}
