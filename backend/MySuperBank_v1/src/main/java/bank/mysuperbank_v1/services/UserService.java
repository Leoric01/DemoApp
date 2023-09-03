package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserRequestDto;
import bank.mysuperbank_v1.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<?> registerUser(UserRequestDto userRequestDto);

    boolean isEmailValid(String email);

    boolean verifySameName(String username);
    boolean verifySameEmail(String email);
}
