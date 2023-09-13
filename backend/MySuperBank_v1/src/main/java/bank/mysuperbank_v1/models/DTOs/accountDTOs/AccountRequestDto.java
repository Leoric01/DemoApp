package bank.mysuperbank_v1.models.DTOs.accountDTOs;

import java.math.BigDecimal;

public class AccountRequestDto {
    private String accountNumber;
    private String accountName;
    private String accountType;
    private BigDecimal balance;

    public AccountRequestDto() {
    }

    public AccountRequestDto(String accountNumber, String accountName, String accountType, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
