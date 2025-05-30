package com.example.Blogify.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	 @Autowired
	    private JWTTokenHelper jwtTokenHelper;

	    @Autowired
	    private CustomUserDetailService userDetailService;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain)
	            throws ServletException, IOException {

	        String requestTokenHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwtToken = null;

	        // Token is in the format "Bearer token"
	        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	            jwtToken = requestTokenHeader.substring(7);
	            try {
	                username = jwtTokenHelper.getUsernameFromToken(jwtToken);
	            } catch (Exception e) {
	                // If token extraction fails, send a 401 Unauthorized response
	                System.out.println("Invalid token: " + e.getMessage());
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.getWriter().write("Invalid or expired token");
	                return;
	            }
	        }

	        // Token is valid, authenticate user
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = userDetailService.loadUserByUsername(username);

	            if (jwtTokenHelper.validateToken(jwtToken, userDetails.getUsername())) {
	                UsernamePasswordAuthenticationToken authToken =
	                        new UsernamePasswordAuthenticationToken(
	                                userDetails, null, userDetails.getAuthorities());

	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }

	        filterChain.doFilter(request, response);
	    }

	    @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	        String path = request.getRequestURI();
	        return path.startsWith("/api/auth");
	    }

}
