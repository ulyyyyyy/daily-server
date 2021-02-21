package com.heihei.daily.domains.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String nickName;

    private String avatarUrl;

    private boolean owner;

    public void replace(User user) {
        if (this.getId() == null || this.getId().length() == 0) {
            this.setId(user.getId());
        }
        this.setNickName(user.getNickName());
        this.setAvatarUrl(user.getAvatarUrl());
        this.setOwner(user.isOwner());
    }
}
