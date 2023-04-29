package com.backendnotebook.services.user.model;

import com.backendnotebook.common.models.UserInfo;

public class UserInfoRdo {

    public Integer id;
    public String email;
    public String username;
    public Long createat;

    public UserInfoRdo() {
    }

    public UserInfoRdo(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.email = userInfo.getEmail();
        this.username = userInfo.getName();
        this.createat = userInfo.getCreatedat().getTime();
    }

    public UserInfoRdo(Integer id, String email, String username, Long createat) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.createat = createat;
    }
}
