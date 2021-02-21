package com.heihei.daily.services;


import com.heihei.daily.domains.models.user.User;
import com.heihei.daily.domains.storage.UserRepository;
import com.heihei.daily.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpService httpService;

    @Value(value = "${wxUrls.openId}")
    String openId;

    public String createUser(User user) {
        User retUser = userRepository.save(user);
        System.out.println(retUser);
        return retUser.getId();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    /**
     * @param user 登录时用户
     * @param id openId
     * @return 用户列表
     */
    public User updateUserById(User user, String id) {
        return new User();
    }

    /**
     * 调用api.wx的接口，利用登录时给的code返回用户的openId
     *
     * @param code 登录时的code
     * @return String, openId
     */
    public String getOpenId(String code) {
        String url = String.format(openId, code);
        String data = httpService.request(url, HttpMethod.GET, null, String.class);
        if (data != null) {
            Map<String, String> dataMap = (Map<String, String>) JsonUtils.jsonToMap(data);
            return dataMap.get("openid");
        } else {
            return "";
        }
    }

    /**
     * 用户登录入库方法
     *
     * @param user   登录用户
     * @param openId 用户的openId
     */
    public void userLogin(User user, String openId) {
        User userDb;
        // TODO: 2021/2/21  需要判断是否为owner，目前使用白名单的方法
        Optional<User> userOptional = userRepository.findById(openId);
        if (userOptional.isPresent()) {
            userDb = userOptional.get();
            // 替换数据
            userDb.replace(user);
        } else {
            userDb = user;
            userDb.setId(openId);
        }
        userRepository.save(userDb);
    }

}
