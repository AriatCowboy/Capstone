package Game.utility;

import Game.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtConverter {
    private final SecretKeySpec secretKeySpec;

    private final String ISSUER = "hedge-api";
    private final int EXPIRATION_MINUTES = 1440; // 24 hours
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public JwtConverter(ApiProperties apiProperties) {
        secretKeySpec = new SecretKeySpec(
                apiProperties.getJwtSecretKey().getBytes(), HS256.getJcaName());
    }

    public String getTokenFromUser(User user) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .claim("userId", user.getUserId())
                .claim("password", user.getPassword())
                .claim("disabled", user.isDisabled())
                .claim("roles", String.join(",", user.getRoles()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(secretKeySpec)
                .compact();
    }

    public User getUserFromToken(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(secretKeySpec)
                    .build()
                    .parseClaimsJws(token);

            String username = jws.getBody().getSubject();

            Claims claims = jws.getBody();
            String userId = (String)claims.get("userId");
            String password = (String)claims.get("password");
            boolean disabled = (boolean)claims.get("disabled");
            String rolesString = (String)claims.get("roles");
            String[] roles = rolesString.split(",");

            return new User(userId, username, password, disabled, roles);

        } catch (JwtException e) {
            System.out.println(e);
        }

        return null;
    }
}
