package beyondeyesight.user.infra.security;

import beyondeyesight.user.domain.model.user.User;
import beyondeyesight.user.domain.service.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider implements TokenProvider {

    private static final int EXPIRE_DURATION = 864000000;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.issuer}")
    private String jwtIssuer;
    @Value("${jwt.type}")
    private String jwtType;
    @Value("${jwt.audience}")
    private String jwtAudience;

    @Override
    public String generate(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
            .setHeaderParam("type", jwtType)
            .setIssuer(jwtIssuer)
            .setAudience(jwtAudience)
            .setSubject(user.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .compact();
    }
}
