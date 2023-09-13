package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountNameRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountRequestDto;
import bank.mysuperbank_v1.models.DTOs.accountDTOs.AccountResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService{
    ResponseEntity<List<AccountResponseDto>> getAllAccounts(HttpServletRequest request);
    ResponseEntity<?> addNewAccount(HttpServletRequest request, AccountRequestDto requestDto);

    ResponseEntity<?> changeName(HttpServletRequest request, AccountNameRequestDto requestDto);
}
