package com.backendnotebook.services.user.service;

import com.backendnotebook.securityconfiguration.SecurityConfig;
import com.backendnotebook.services.user.model.UserInfoQdo;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.common.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserInfoServiceIml implements UserInfoService {

    private UserInfoRepository userInfoRepository;
    private SecurityConfig securityConfig;
    @Override
    public String addUser(UserInfoQdo userInfoQdo) {

        UserInfo userInfo = prepareUserInfo(null, userInfoQdo, "USER");
        userInfo = userInfoRepository.save(userInfo);
        return "user added successfully";
    }

    @Override
    public UserInfo getUserById(Integer userId) {
        return userInfoRepository.findById(userId).orElse(null);
    }

    @Override
    public Optional<UserInfo> findByName(String username) {
        return userInfoRepository.findByName(username);
    }

    private UserInfo prepareUserInfo(Integer id, UserInfoQdo userInfoQdo, String role) {
        UserInfo userInfo;
        if (id == null) {
            userInfo = new UserInfo();
        } else {
            Optional<UserInfo> userInfoOptional = userInfoRepository.findById(id);
            userInfo = userInfoOptional.get();
        }
        userInfo.setName(userInfoQdo.username);
        userInfo.setPassword(securityConfig.passwordEncoder().encode(userInfoQdo.password));
        userInfo.setEmail(userInfoQdo.email);
        userInfo.setRoles(role);
        userInfo.setCreatedat(new Date(System.currentTimeMillis()));

        return userInfo;
    }

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setSecurityConfig(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }
}
