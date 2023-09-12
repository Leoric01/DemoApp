package bank.mysuperbank_v1.models.DTOs.roleDTOs;

public class RoleRequestDto {
    private String name;

    public RoleRequestDto(String name) {
        this.name = name;
    }

    public RoleRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
