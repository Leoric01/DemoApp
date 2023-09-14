package bank.mysuperbank_v1.models.DTOs.accountDTOs;

public class AccountDelDto {
    private Long ownerId;
    private String accountName;

    public AccountDelDto() {
    }

    public AccountDelDto(Long ownerId, String accountName) {
        this.ownerId = ownerId;
        this.accountName = accountName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
