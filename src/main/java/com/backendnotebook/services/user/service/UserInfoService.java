package com.backendnotebook.services.user.service;

import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.services.user.model.AuthRequestQdo;
import com.backendnotebook.services.user.model.UserInfoQdo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserInfoService {
    public String addUser(UserInfoQdo userInfoQdo);

    UserInfo getUserById(Integer userId);

    Optional<UserInfo> findByName(String username);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
