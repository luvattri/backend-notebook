package com.backendnotebook.services.user.service;

import com.backendnotebook.common.service.PasswordEncoderService;
import com.backendnotebook.securityconfiguration.UserInfoUserDetails;
import com.backendnotebook.services.user.model.UserInfoQdo;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.common.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserInfoServiceIml implements UserInfoService {

    private UserInfoRepository userInfoRepository;
    private PasswordEncoderService passwordEncoderService;

    @Override
    public String addUser(UserInfoQdo userInfoQdo) {

        UserInfo userInfo = prepareUserInfo(null, userInfoQdo, "USER");
        userInfo = userInfoRepository.save(userInfo);
        return "user added successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found " + username));
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
            userInfo.setCreatedat(new Date(System.currentTimeMillis()));
        } else {
            Optional<UserInfo> userInfoOptional = userInfoRepository.findById(id);
            userInfo = userInfoOptional.get();
        }
        userInfo.setName(userInfoQdo.username);
        userInfo.setPassword(passwordEncoderService.passwordEncoder().encode(userInfoQdo.password));
        userInfo.setEmail(userInfoQdo.email);
        userInfo.setRoles(role);
        userInfo.setUpdatedat(new Date(System.currentTimeMillis()));

        return userInfo;
    }

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setPasswordEncoderService(PasswordEncoderService passwordEncoderService) {
        this.passwordEncoderService = passwordEncoderService;
    }
}
