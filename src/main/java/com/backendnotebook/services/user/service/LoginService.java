package com.backendnotebook.services.user.service;

import com.backendnotebook.services.user.model.AuthRequestQdo;

public interface LoginService {
    public String authenticateAndGenrateToken(AuthRequestQdo authRequestQdo);
}
