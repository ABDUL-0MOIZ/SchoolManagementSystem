package com.example.school_management_system.config;

import com.example.school_management_system.service.CustomUserDetailService;
import com.example.school_management_system.service.JwtService;
import com.example.school_management_system.service.RedisService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;
    private final RedisService redisService;
    public JwtAuthenticationFilter(JwtService jwtService,RedisService redisService, CustomUserDetailService customUserDetailService) {
        this.jwtService = jwtService;
        this.redisService=redisService;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String header= request.getHeader("Authorization");
    if(header ==null || !header.startsWith("Bearer ")){
        filterChain.doFilter(request,response);
        return;
    }
   try {
       String token = header.substring(7);
       if(redisService.isBlacklisted(token)){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.setContentType("application/json");
           response.getWriter().write(

                   "{\"status\":401,\"message\":\"Token has been blacklisted\"}"
           );
           return;
       }
       String email = jwtService.extractEmail(token);
       if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

           if (jwtService.ValidateToken(token, userDetails)) {

               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities()
               );
               authenticationToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request)
               );
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }

       }
   }catch (JwtException ex) {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        response.getWriter().write(
                "{\"status\":401,\"message\":\"Invalid JWT token\"}"
        );

        return;
    }
    filterChain.doFilter(request,response);
    }

}
