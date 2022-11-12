package com.topprofessors.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.topprofessors.Component.JwtUtil;
import com.topprofessors.Service.UserService;

import io.jsonwebtoken.SignatureException;

@Component
public class JwtUserAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		//System.out.println("requestTokenHeader: " + requestTokenHeader);
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			String accessToken = requestTokenHeader.substring(7);
			//System.out.println("accessToken: " + accessToken);

			try {
				String idAsString = jwtUtil.extractUsername(accessToken);
	            int id = Integer.parseInt(idAsString);
				UserDetails userDetails = userService.loadUserById(id);
				
				if(idAsString != null && SecurityContextHolder.getContext().getAuthentication()  == null) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				
			} catch(SignatureException e) {
				System.out.println("JwtUserAuthenticationFilter doFilterInternal Wrong accessToken");
			}
	        catch (NumberFormatException ex){
				System.out.println("JwtUserAuthenticationFilter doFilterInternal NumberFormatException");
	            //ex.printStackTrace();
	        }
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
