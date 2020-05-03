package az.softspark.springsecurity.security.model;

public class AuthenticationResponse {
    private final String access_token;

    public AuthenticationResponse(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
