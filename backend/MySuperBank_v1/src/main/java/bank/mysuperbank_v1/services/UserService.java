package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.UserRequestDto;
import bank.mysuperbank_v1.models.DTOs.UserResponseDto;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.security.authentication.AuthenticationRequest;
import bank.mysuperbank_v1.security.authentication.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<?> registerUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUserResponseDto();

    boolean isEmailValid(String email);

    boolean verifySameName(String username);
    boolean verifySameEmail(String email);

    UserDetails loadUserByUsername(String username);

    AuthenticationResponse login(AuthenticationRequest loginDetails);
    boolean verifyUser(String username, String password);
    String generateToken(String username, String password);
}
