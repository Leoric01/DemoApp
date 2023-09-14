package bank.mysuperbank_v1.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String accountName;
    private String accountType;
    private BigDecimal balance;
    private Long created_at;
    private Long updated_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<TransactionHistory> transactionHistories = new ArrayList<>();

    @OneToMany
    private List<Payment> payments = new ArrayList<>();

    public Account(String accountNumber, String accountName, String accountType, BigDecimal balance, User user) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
        this.created_at = Instant.now().getEpochSecond();
        this.updated_at = Instant.now().getEpochSecond();

    }

    public Account(String accountNumber, String accountName, String accountType) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = BigDecimal.valueOf(0);
        this.created_at = Instant.now().getEpochSecond();
    }

    public Account() {
        this.created_at = Instant.now().getEpochSecond();
    }

    public String getFormattedCreatedAt() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(created_at), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public String getFormattedUpdatedAt() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(updated_at), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }
}
