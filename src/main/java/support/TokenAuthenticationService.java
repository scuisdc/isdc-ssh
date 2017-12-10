package support;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * TokenAuthenticationService.java
 * Created on 2017/8/10 17:07
 *
 * @author Amao
 * @version 1.0.1
 */
@Component
public class TokenAuthenticationService {
    public static final long EXPIRATION = 864_000_000;
    private static String SECRET;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public static String addAuthentication(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static void setSECRET(String SECRET) {
        TokenAuthenticationService.SECRET = SECRET;
    }

    public static Optional<Integer> getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String userId = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return Optional.ofNullable(userId).map(Integer::parseInt);
        }
        return Optional.empty();
    }
}