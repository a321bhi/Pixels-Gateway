package com.pixels.pixelsgateway.security;

import org.springframework.stereotype.Component;

import com.pixels.pixelsgateway.exception.JwtTokenIncorrectStructureException;
import com.pixels.pixelsgateway.exception.JwtTokenMalformedException;
import com.pixels.pixelsgateway.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	private final JwtConfig config;

	public JwtTokenUtil(JwtConfig config) {
		this.config = config;
	}

//	public String generateToken(String id) {
//		Claims claims = Jwts.claims().setSubject(id);
//		long nowMillis = System.currentTimeMillis();
//		long expMillis = nowMillis + config.getValidity() * 1000 * 60;
//		Date exp = new Date(expMillis);
//		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
//				.signWith(SignatureAlgorithm.HS512, config.getSecret()).compact();
//	}

	public void validateToken(final String header) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			String[] parts = header.split(" ");
			if (parts.length != 2 || !"Bearer".equals(parts[0])) {
				throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure");
			}
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(config.getSecretKeySigned()).build()
					.parseClaimsJws(parts[1]);
			Claims body = claimsJws.getBody();
			String username = body.getSubject();
			Jwts.parser().setSigningKey(config.getSecretKeySigned()).parseClaimsJws(parts[1]);
			
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
	public String getUsername(final String header) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			String[] parts = header.split(" ");
			if (parts.length != 2 || !"Bearer".equals(parts[0])) {
				throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure");
			}
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(config.getSecretKeySigned()).build()
					.parseClaimsJws(parts[1]);
			Claims body = claimsJws.getBody();
			String username = body.getSubject();
			return username;
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
}