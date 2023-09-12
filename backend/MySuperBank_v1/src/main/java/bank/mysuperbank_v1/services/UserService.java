package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserPutRequestDto;
import bank.mysuperbank_v1.models.DTOs.UserRequestDto;
import bank.mysuperbank_v1.models.DTOs.UserResponseDto;
import bank.mysuperbank_v1.security.authentication.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
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

    ResponseEntity<?> login(AuthenticationRequest loginDetails);
    boolean verifyUser(String username, String password);
    String generateToken(String username, String password);

    ResponseEntity<?> getUserDto(Long id);

    ResponseEntity<?> extractFromToken(HttpServletRequest request);

    ResponseEntity<?> changeUserData(Long id, HttpServletRequest request, UserPutRequestDto requestDto);

    ResponseEntity<?> deleteUserById(Long id, HttpServletRequest request);
}
