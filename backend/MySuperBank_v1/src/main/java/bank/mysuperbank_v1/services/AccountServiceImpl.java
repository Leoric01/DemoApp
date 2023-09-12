package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountResponseDto;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.AccountRepository;
import bank.mysuperbank_v1.repositories.UserRepository;
import bank.mysuperbank_v1.security.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

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
    public ResponseEntity<List<AccountResponseDto>> getAllAccounts(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).build();
        }
        final String jwt = authHeader.substring(7);
        User user = userRepository.findUserByUsername(jwtService.extractUsername(jwt));
        if (user.getRole().getName().equals("ADMIN")){
            return ResponseEntity.status(200).body(accountRepository.findAll().stream()
                    .map(acc -> {
                        return new AccountResponseDto(acc.getId(), acc.getAccountNumber(), acc.getAccountName(),
                                acc.getAccountType(), acc.getBalance(), acc.getCreated_at(), acc.getUpdated_at());
                    }).collect(Collectors.toList()));
        }
        return ResponseEntity.status(403).build();
    }
}
