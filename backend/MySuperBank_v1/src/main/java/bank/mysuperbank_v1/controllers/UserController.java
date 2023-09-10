package bank.mysuperbank_v1.controllers;

import bank.mysuperbank_v1.models.DTOs.UserResponseDto;
import bank.mysuperbank_v1.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my-details")
    public ResponseEntity<?> userDetail(@NotNull HttpServletRequest request){
        return userService.extractFromToken(request);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> listUsers(){
        return ResponseEntity.status(200).body(userService.getAllUserResponseDto());
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> showUser(@PathVariable Long id){
        return userService.getUserDto(id);
    }

}
