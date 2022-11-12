package com.topprofessors.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    private final String SECRET_KEY = "XXXXXXXXXXXXXXXXXXXXXXXX";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        if(claims == null)return null;
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
    	try {
    		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    	} catch(ExpiredJwtException e) {
    		return null;
    	}
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

    	//System.out.println("new Date(System.currentTimeMillis()): " + new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 25));
    	Calendar currentDateDalendar = Calendar.getInstance();
    	currentDateDalendar.setTime(new Date());
    	currentDateDalendar.add(Calendar.DATE, 180);
    	//System.out.println("currentDate + 180: " + currentDateDalendar.getTime());
    	
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                //.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 25))
        		.setExpiration(currentDateDalendar.getTime())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	try {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    	} catch(Exception e) {
    		return false;
    	}
    }
}