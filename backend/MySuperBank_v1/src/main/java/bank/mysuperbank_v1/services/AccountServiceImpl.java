package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.Account;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountResponseDto;
import bank.mysuperbank_v1.models.DTOs.statusDTOs.ErrorResponse;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.AccountRepository;
import bank.mysuperbank_v1.repositories.UserRepository;
import bank.mysuperbank_v1.security.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, JwtService jwtService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<List<AccountResponseDto>> getAllAccounts(@NotNull HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).build();
        }
        final String jwt = authHeader.substring(7);
        User user = userRepository.findUserByUsername(jwtService.extractUsername(jwt));
        if (user.getRole().getName().equals("ADMIN")) {
            return ResponseEntity.status(200).body(accountRepository.findAll().stream()
                    .map(acc -> {
                        return new AccountResponseDto(acc.getId(), acc.getUser().getUsername(), acc.getAccountNumber(), acc.getAccountName(),
                                acc.getAccountType(), acc.getBalance(), acc.getFormattedCreatedAt(), acc.getFormattedUpdatedAt());
                    }).collect(Collectors.toList()));
        }
        return ResponseEntity.status(403).build();
    }
    @Override
    public ResponseEntity<?> addNewAccount(HttpServletRequest request, AccountRequestDto requestDto) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(new ErrorResponse("Bearer token error"));
        }
        final String jwt = authHeader.substring(7);
        User user = userRepository.findUserByUsername(jwtService.extractUsername(jwt));
        if (requestDto.getAccountNumber() == null || requestDto.getAccountNumber().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Account number is mandatory"));
        }
        if (requestDto.getAccountName() == null || requestDto.getAccountName().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Account name is mandatory"));
        }
        if (requestDto.getAccountType() == null || requestDto.getAccountType().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Account type is mandatory"));
        }
        if (requestDto.getBalance() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse("Starting balance is mandatory"));
        }
        Account account = new Account(requestDto.getAccountNumber(), requestDto.getAccountName(), requestDto.getAccountType(), requestDto.getBalance(), user);
        accountRepository.save(account);
        AccountResponseDto responseDto = new AccountResponseDto(account.getId(), account.getUser().getUsername() ,account.getAccountNumber(),account.getAccountName(), account.getAccountType(), account.getBalance(), account.getFormattedCreatedAt(), account.getFormattedUpdatedAt());
        if (responseDto.getCreated_at().equals(responseDto.getUpdated_at())){
            responseDto.setUpdated_at("");
        }
        return ResponseEntity.status(200).body(responseDto);
    }
}
