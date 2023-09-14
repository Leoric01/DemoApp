package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.Account;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountNameRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountResponseDto;
import bank.mysuperbank_v1.models.DTOs.statusDTOs.ErrorResponse;
import bank.mysuperbank_v1.models.DTOs.statusDTOs.SuccessResponse;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.AccountRepository;
import bank.mysuperbank_v1.repositories.UserRepository;
import bank.mysuperbank_v1.security.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
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
                        String createdAt = acc.getFormattedCreatedAt();
                        String updatedAt = acc.getFormattedUpdatedAt();
                        if (createdAt.equals(updatedAt)) {
                            updatedAt = "No changes yet";
                        }
                        return new AccountResponseDto(
                                acc.getId(),
                                acc.getUser().getUsername(),
                                acc.getAccountNumber(),
                                acc.getAccountName(),
                                acc.getAccountType(),
                                acc.getBalance(),
                                createdAt,
                                updatedAt);
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
        if (accountRepository.existsByAccountNumber(requestDto.getAccountNumber())){
            return ResponseEntity.status(409).body("Account with same number already exists");
        }
        Account account = new Account(requestDto.getAccountNumber(), requestDto.getAccountName(), requestDto.getAccountType(), requestDto.getBalance(), user);
        accountRepository.save(account);
        AccountResponseDto responseDto = new AccountResponseDto(account.getId(), account.getUser().getUsername(), account.getAccountNumber(), account.getAccountName(), account.getAccountType(), account.getBalance(), account.getFormattedCreatedAt(), account.getFormattedUpdatedAt());
        if (responseDto.getCreated_at().equals(responseDto.getUpdated_at())) {
            responseDto.setUpdated_at("No changes yet");
        }
        return ResponseEntity.status(200).body(responseDto);
    }

    @Transactional
    @Override
    public ResponseEntity<?> changeName(HttpServletRequest request, AccountNameRequestDto requestDto) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body(new ErrorResponse("Bearer token error"));
        }
        final String jwt = authHeader.substring(7);
        User user = userRepository.findUserByUsername(jwtService.extractUsername(jwt));
        Account acc;
        if (requestDto.getId() == null) {
            return ResponseEntity.status(400).body(new ErrorResponse("Id of the owner of the account is mandatory"));
        }
        if (requestDto.getCurrentName() == null || requestDto.getCurrentName().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Name of the account is mandatory"));
        }
        if (requestDto.getNewName() == null || requestDto.getNewName().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("New name of the account is mandatory"));
        }
        if (!userRepository.existsById(requestDto.getId())){
            return ResponseEntity.status(404).body(new ErrorResponse("User with id " + requestDto.getId() + " doesn't exist"));
        }
        List<Account> accounts = user.getAccounts();
        if (user.getId() == requestDto.getId()) {
            for (Account account : accounts) {
                if (account.getAccountName().equals(requestDto.getCurrentName())) {
                    acc = account;
                    acc.setAccountName(requestDto.getNewName());
                    accountRepository.save(acc);
                    return ResponseEntity.status(201).body(new SuccessResponse("Name successfully changed to: " + acc.getAccountName()));
                }
            }
            return ResponseEntity.status(404).body(new ErrorResponse("User with id " + requestDto.getId() + " doesnt have account with name: " + requestDto.getCurrentName()));
        }
        if (!user.getRole().equals("ADMIN")) {
            return ResponseEntity.status(403).body(new ErrorResponse("You must be role ADMIN to change account that ain't yours"));
        }
        User client = userRepository.findUserById(requestDto.getId());
        for (Account account : client.getAccounts()){
            if (account.getAccountName().equals(requestDto.getCurrentName())){
                account.setAccountName(requestDto.getNewName());
                accountRepository.save(account);
                return ResponseEntity.status(201).body(new SuccessResponse("Account name of the user with id " + requestDto.getId() + " was changed to: "+requestDto.getNewName()));
            }
        }
        return ResponseEntity.status(404).body(new ErrorResponse("User with id " + requestDto.getId() + " doesnt have account with name: " + requestDto.getCurrentName()));
    }
}
