package com.backendnotebook.common.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface PasswordEncoderService {

    public PasswordEncoder passwordEncoder();
}
