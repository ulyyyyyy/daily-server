package com.heihei.daily.controllers;

import com.heihei.daily.domains.MyResponseContent;
import com.heihei.daily.domains.models.Info.DoneInfo;
import com.heihei.daily.domains.models.Info.Info;
import com.heihei.daily.domains.models.Info.TodoInfo;
import com.heihei.daily.services.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = "土豆数据's Api")
public class InfoController {

    @Autowired
    InfoService services;

    @GetMapping("/todo/info")
    @ApiOperation("获取当前Info List数据")
    public MyResponseContent<Info> getAll(@RequestParam("userId") String userId) {
        return new MyResponseContent<>(true, services.getAllInfo(userId), "请求成功", 200);
    }

    @PostMapping("/todo/info")
    @ApiOperation("提交一个新的todo list")
    public Object createNewTodo(@RequestBody TodoInfo todoInfo,
                                @RequestParam("userId") String userId) {
        return services.createInfo(todoInfo, userId);
    }

    @PatchMapping("/todo/info/{id}")
    @ApiOperation("修改一个新的todo list")
    public Object updateTodo(@RequestBody TodoInfo todoInfo,
                             @PathVariable("id") String id,
                             @RequestParam("userId") String userId) {
        return services.updateTodo(todoInfo, id, userId);
    }

    @DeleteMapping("/todo/info/{id}")
    @ApiOperation("删除一个todo list")
    public Object deleteTodo(@PathVariable("id") String id,
                             @RequestParam("userId") String userId) {
        return services.deleteTodo(id, userId);
    }

    @PostMapping("/done/info")
    @ApiOperation("获取当前Done List数据")
    public DoneInfo createNewDone(@RequestBody DoneInfo doneInfo,
                                  @RequestParam("userId") String userId) {
        return services.createDone(doneInfo, userId);
    }

    @PatchMapping("/done/info/{id}")
    @ApiOperation("修改一个新的done list")
    public Object updateTodo(@RequestBody DoneInfo doneInfo,
                             @PathVariable("id") String id,
                             @RequestParam("userId") String userId) {
        return services.updateDone(doneInfo, id, userId);
    }

    @DeleteMapping("/done/info/{id}")
    @ApiOperation("删除一个todo list")
    public Object deleteDone(@PathVariable("id") String id,
                             @RequestParam("userId") String userId) {
        return services.deleteDone(id, userId);
    }
}
