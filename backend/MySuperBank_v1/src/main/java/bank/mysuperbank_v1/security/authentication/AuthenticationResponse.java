package bank.mysuperbank_v1.security.authentication;

public class AuthenticationResponse {
    public String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
