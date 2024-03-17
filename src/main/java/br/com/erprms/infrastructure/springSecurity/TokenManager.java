package br.com.erprms.infrastructure.springSecurity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.erprms.domainModel.LoginUser;

@Service
public class TokenManager {

	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(LoginUser user) {
		
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
						.withIssuer("ERP_rms")
						.withSubject(user.getLogin())
						.withExpiresAt(dataExpiracao())
						.sign(algorithm);
		   } catch (JWTCreationException exception){
	            throw new RuntimeException("Error generating JWT token", exception);
	       }
	}
	
	public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("ERP_rms")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT Token!");
        }
    }
	
	private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
