package com.kode4you.getyourquote.spring.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "authorization";

    public static void addAuthentication(HttpServletResponse res, String username) {
        String jwt = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "*");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Expose-Headers", "*");
    }

    public static Authentication getAuthentication(HttpServletRequest request) throws Exception {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String login = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
            return login != null ? new UsernamePasswordAuthenticationToken(login, null, Collections.emptyList()) : null;
        }
        return null;
    }
}
