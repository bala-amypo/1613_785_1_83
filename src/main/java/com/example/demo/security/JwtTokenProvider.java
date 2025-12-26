public class JwtTokenProvider {

    private String secretKey;
    private long validityInMs;

    public JwtTokenProvider(String secretKey, long validityInMs) {
        this.secretKey = secretKey;
        this.validityInMs = validityInMs;
    }

    public String createToken(String username, String role, long validity) {
        // implement token creation
    }

    public Claims getClaims(String token) {
        // implement claim extraction
    }
}
