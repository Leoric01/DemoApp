package bank.mysuperbank_v1.models.DTOs.statusDTOs;

public class SuccessResponse {
    public String success;

    public SuccessResponse() {
    }

    public SuccessResponse(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
