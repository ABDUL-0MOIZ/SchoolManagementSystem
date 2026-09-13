package com.example.school_management_system.security;

import com.example.school_management_system.Model.Role;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.service.CustomUserDetailService;
import com.example.school_management_system.service.JwtService;
import com.example.school_management_system.service.RefreshTokenService;
import com.example.school_management_system.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public OAuthSuccessHandler(JwtService jwtService,RefreshTokenService refreshTokenService,CustomUserDetailService customUserDetailService, UserService userService) {
        this.jwtService = jwtService;
        this.refreshTokenService=refreshTokenService;
        this.customUserDetailService = customUserDetailService;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

  OAuth2User oAuth2User=(OAuth2User) authentication.getPrincipal();
    String email=oAuth2User.getAttribute("email");
    String name=oAuth2User.getAttribute("name");
        User user=userService.findUserByEmail(email);
    if(user==null){
        user=new User();
        user.setEmail(email);
        user.setUserName(name);
        user.setRole(List.of(Role.Student));
        userService.createUser(user);

    }
        UserDetails userDetails =
                customUserDetailService.loadUserByUsername(email);
        String token =
                jwtService.junrateToken(userDetails);
        String refreshtoken =refreshTokenService.createRefreshToke(user).getToken();
        response.setContentType("application/json");
        response.getWriter().write("{\"jwtToken\":\""+token+"\" \n \"refreshToken\":\""+refreshtoken+"\"}");
    }
}
