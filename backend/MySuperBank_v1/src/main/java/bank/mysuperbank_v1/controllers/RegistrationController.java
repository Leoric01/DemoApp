package bank.mysuperbank_v1.controllers;

import bank.mysuperbank_v1.models.DTOs.UserDto;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        return null;
    }
}
