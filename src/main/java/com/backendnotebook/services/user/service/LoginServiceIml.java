package com.backendnotebook.services.user.service;

import com.backendnotebook.securityconfiguration.services.JwtService;
import com.backendnotebook.services.user.model.AuthRequestQdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceIml implements LoginService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Override
    public String authenticateAndGenrateToken(AuthRequestQdo authRequestQdo) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestQdo.getUsername(), authRequestQdo.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generaateToken(authRequestQdo.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }
}
