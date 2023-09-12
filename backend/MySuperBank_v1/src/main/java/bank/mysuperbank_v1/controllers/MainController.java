package bank.mysuperbank_v1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    @GetMapping({"", "/"})
    @ResponseBody
    public String main() {
        return "index";
    }
}
