package com.heihei.daily.controllers;

import com.heihei.daily.domains.models.user.User;
import com.heihei.daily.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户数据API")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/openid")
    @ApiOperation("根据code返回User openId")
    public String getOpenID(@RequestParam("id") String code,
                            @RequestBody User user) {
        String openid = userService.getOpenId(code);
        userService.userLogin(user, openid);
        return openid;
    }

    @PostMapping("/user")
    @ApiOperation("创建用户")
    public String createUser(User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    @ApiOperation("查询所有用户")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("查询所有用户")
    public User findUserById(@PathVariable("userId") String id) {
        return userService.findUserById(id);
    }

    @PutMapping("/user/{userId}")
    @ApiOperation("更新单个用户数据")
    public User updataUserById(@RequestBody User user,
                               @PathVariable("userId") String id) {
        return userService.updateUserById(user, id);
    }
}
