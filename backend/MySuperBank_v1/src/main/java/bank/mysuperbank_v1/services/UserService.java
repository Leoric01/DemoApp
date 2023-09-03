package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserRequestDto;
import bank.mysuperbank_v1.models.DTOs.UserResponseDto;
import bank.mysuperbank_v1.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<?> registerUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUserResponseDto();

    boolean isEmailValid(String email);

    boolean verifySameName(String username);
    boolean verifySameEmail(String email);
}
