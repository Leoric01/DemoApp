package bank.mysuperbank_v1.models;

import java.util.Objects;

public class ErrorResponse {
    private String error;

    public ErrorResponse(String error){
        this.error = error;
    }

    //added for tests to work correctly
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(error, that.error);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
