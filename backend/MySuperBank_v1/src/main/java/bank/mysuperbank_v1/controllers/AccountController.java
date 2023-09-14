package bank.mysuperbank_v1.controllers;

import bank.mysuperbank_v1.models.Account;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountNameRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountResponseDto;
import bank.mysuperbank_v1.services.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAllAccounts(@NotNull HttpServletRequest request){
        return accountService.getAllAccounts(request);
    }

    @PostMapping("/account")
    public ResponseEntity<?> addNewAccount(@NotNull HttpServletRequest request, @RequestBody AccountRequestDto requestDto){
        return accountService.addNewAccount(request, requestDto);
    }

    @PutMapping("/account")
    public ResponseEntity<?> changeName(@NotNull HttpServletRequest request, @RequestBody AccountNameRequestDto requestDto){
        return accountService.changeName(request, requestDto);
    }
}
