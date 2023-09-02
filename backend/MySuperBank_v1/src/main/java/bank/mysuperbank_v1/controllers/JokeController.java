package bank.mysuperbank_v1.controllers;


import bank.mysuperbank_v1.models.DTOs.JokeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joke/api")
@CrossOrigin(origins = "http://localhost:3000")
public class JokeController {

    @GetMapping
    public ResponseEntity<JokeDto> jokes(){
        JokeDto jokeDto = new JokeDto("this is setup", "this is joke's punchline");
        return ResponseEntity.status(HttpStatus.CREATED).body(jokeDto);
    }
}
