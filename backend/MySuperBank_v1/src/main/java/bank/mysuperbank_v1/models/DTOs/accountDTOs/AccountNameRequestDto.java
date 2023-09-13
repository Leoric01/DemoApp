package bank.mysuperbank_v1.models.DTOs.accountDTOs;

public class AccountNameRequestDto {
    private Long id;
    private String currentName;
    private String newName;

    public AccountNameRequestDto() {
    }

    public AccountNameRequestDto(Long id, String currentName, String newName) {
        this.id = id;
        this.currentName = currentName;
        this.newName = newName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
