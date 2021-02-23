package com.heihei.daily.domains.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Field("id")
    private String  id;

    private String nickName;

    private String avatarUrl;

    private List<String> allowedInfoList;

    public void replace(User user) {
        if (this.getId() == null || this.getId().length() == 0) {
            this.setId(user.getId());
        }
        this.setNickName(user.getNickName());
        this.setAvatarUrl(user.getAvatarUrl());
    }
}
