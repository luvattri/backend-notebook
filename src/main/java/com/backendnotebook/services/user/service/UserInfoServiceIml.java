package com.backendnotebook.services.user.service;

import com.backendnotebook.common.service.PasswordEncoderService;
import com.backendnotebook.securityconfiguration.UserInfoUserDetails;
import com.backendnotebook.services.user.model.UserInfoQdo;
import com.backendnotebook.common.models.UserInfo;
import com.backendnotebook.common.repository.UserInfoRepository;
import com.backendnotebook.services.user.model.UserInfoRdo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceIml implements UserInfoService {

    private UserInfoRepository userInfoRepository;
    private PasswordEncoderService passwordEncoderService;
    private EntityManager entityManager;

    @Override
    public UserInfoRdo addUser(UserInfoQdo userInfoQdo) {

        UserInfo userInfo = prepareUserInfo(null, userInfoQdo, "USER");
        userInfo = userInfoRepository.save(userInfo);
        return new UserInfoRdo(userInfo);
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
        userInfo.setName(userInfoQdo.email);
        userInfo.setPassword(passwordEncoderService.passwordEncoder().encode(userInfoQdo.password));
        userInfo.setEmail(userInfoQdo.email);
        userInfo.setRoles(role);
        userInfo.setUpdatedat(new Date(System.currentTimeMillis()));

        return userInfo;
    }

    @Override
    public Boolean userNameExsistes(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<UserInfo> root = query.from(UserInfo.class);

        predicates.add(builder.equal(root.get("username"), username));

        query.select(root.get("id"));
        query.where(builder.and(predicates.toArray(new Predicate[0])));
        query.distinct(true);
        Integer id = this.entityManager.createQuery(query).getSingleResult();
        if (id == null || id == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean emailExsistes(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<UserInfo> root = query.from(UserInfo.class);

        predicates.add(builder.equal(root.get("email"), email));

        query.select(root.get("id"));
        query.where(builder.and(predicates.toArray(new Predicate[0])));
        query.distinct(true);

        Integer id = this.entityManager.createQuery(query).getResultList().stream().findFirst().orElse(null);
        if (id == null || id == 0) {
            return false;
        } else {
            return true;
        }
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setPasswordEncoderService(PasswordEncoderService passwordEncoderService) {
        this.passwordEncoderService = passwordEncoderService;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
