package com.topprofessors.Middleware;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.topprofessors.Component.JwtUtil;

import io.jsonwebtoken.SignatureException;

@Component
public class UserMiddleware {

	@Autowired
	JwtUtil jwtUtil;
	
	public int getCurrentUserId() {
		int userId = 0;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userIdStr = authentication.getName();
		try {
			userId = Integer.parseInt(userIdStr);
			//System.out.println("userId: " + userId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return userId;
	}
	
	public int getUserIdFromCurrentAccessToken(HttpServletRequest request) {

		int userId = 0;
		
		String requestTokenHeader = request.getHeader("Authorization");
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			String accessToken = requestTokenHeader.substring(7);

			if(accessToken == null)return userId;

			try {
				String idAsString = jwtUtil.extractUsername(accessToken);
				userId = Integer.parseInt(idAsString);
	            
			} catch(SignatureException e) {
				System.out.println("UserMiddleware getUserIdFromCurrentAccessToken Wrong accessToken");
			}
	        catch (NumberFormatException ex){
	            ex.printStackTrace();
	        }
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return userId;
	}
	
}
