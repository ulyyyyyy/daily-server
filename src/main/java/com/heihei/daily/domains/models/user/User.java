package com.heihei.daily.domains.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document( collection = "user")
public class User {

    @Field("id")
    @Id
    private String id;

    private String nickName;

    private String avatarUrl;

    private List<String> allowedInfoList = new ArrayList<>();

    public void replace(User user) {
        if (this.getId() == null || this.getId().length() == 0) {
            this.setId(user.getId());
        }
        this.setNickName(user.getNickName());
        this.setAvatarUrl(user.getAvatarUrl());
    }
}
