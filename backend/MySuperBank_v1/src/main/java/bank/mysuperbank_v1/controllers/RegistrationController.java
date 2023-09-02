package bank.mysuperbank_v1.controllers;

import bank.mysuperbank_v1.event.RegistrationCompleteEvent;
import bank.mysuperbank_v1.models.DTOs.UserDto;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        publisher.publishEvent(new RegistrationCompleteEvent(user, "url"));
        return "Success";
    }
}
