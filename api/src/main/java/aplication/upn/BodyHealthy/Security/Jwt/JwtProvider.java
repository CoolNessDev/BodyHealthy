package aplication.upn.BodyHealthy.Security.Jwt;

import aplication.upn.BodyHealthy.Security.Model.CustomUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String token =Jwts.builder().setSubject(customUserDetails.getUsername())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        System.out.println("Token G: "+token);
        return token;
    }

    public String getUserName(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
    }

    public Boolean validateToker(String jwt) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
            return true;
        } catch (MalformedJwtException e) {
            LOGGER.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Token No soportado "+e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("Token expirado");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token vacio");
        } catch (SignatureException e) {
            LOGGER.error("Fallo en la firma");
        }
        return false;
    }
}
